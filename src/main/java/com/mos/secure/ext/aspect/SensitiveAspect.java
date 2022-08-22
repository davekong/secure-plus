package com.mos.secure.ext.aspect;

import com.mos.secure.ext.annotations.Desensitization;
import com.mos.secure.ext.annotations.DesensitizationProp;
import com.mos.secure.ext.config.SensitiveProp;
import com.mos.secure.ext.util.MosDesensitizedUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;

/**
 * 敏感方面
 *
 * @author 小尘哥
 * @version $Id: $Id
 */
@Aspect
@Slf4j
@Component
public class SensitiveAspect {

    @Resource
    private SensitiveProp sensitiveProp;

    /** Constant <code>ENABLE=true</code> */
    public static final boolean ENABLE = true;

    /**
     *  切点
     */
    @Pointcut(value = "@annotation(com.mos.secure.ext.annotations.Desensitization)")
    public void getPoint() {
    }

    /**
     * 注解脱敏处理
     *
     * @param joinPoint a {@link org.aspectj.lang.ProceedingJoinPoint} object.
     * @return a {@link java.lang.Object} object.
     * @throws java.lang.Throwable if any.
     */
    @Around("getPoint()")
    public Object sensitiveClass(ProceedingJoinPoint joinPoint) throws Throwable {
        if (!sensitiveProp.getEnable()){
            return joinPoint.proceed();
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Desensitization desensitization =method.getAnnotation(Desensitization.class);
        if (desensitization == null || !desensitization.enable()){
            return joinPoint.proceed();
        }
        return sensitiveFormat(joinPoint);
    }

    /**
     * 注解统一拦截器
     *
     * @param joinPoint a {@link org.aspectj.lang.ProceedingJoinPoint} object.
     * @return a {@link java.lang.Object} object.
     * @throws java.lang.Throwable if any.
     */
    public Object sensitiveFormat(ProceedingJoinPoint joinPoint) throws Throwable {
        Object obj = joinPoint.proceed();
        if (obj instanceof List) {
            dealList(obj);
        }  else {
            dealSimpleData(obj);
        }
        return obj;
    }

    private void dealList(Object o) throws IllegalAccessException {
        List<Object> list = (List<Object>) o;
        for (Object obj : list) {
            dealSimpleData(obj);
        }
    }

    private void dealSimpleData(Object obj) throws IllegalAccessException {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            // TODO: 2022/8/22 0022 递归子属性
            DesensitizationProp desensitizationProp = field.getAnnotation(DesensitizationProp.class);
            if (desensitizationProp == null) {
                continue;
            }
            String v = MosDesensitizedUtil.desensitizeData(field.get(obj),desensitizationProp);
            field.set(obj, v);
        }
    }



}

package io.gitee.chemors.secure.ext.aspect;

import cn.hutool.core.util.StrUtil;
import io.gitee.chemors.secure.ext.annotations.Desensitization;
import io.gitee.chemors.secure.ext.annotations.DesensitizationProp;
import io.gitee.chemors.secure.ext.config.SensitiveProp;
import io.gitee.chemors.secure.ext.util.MosDesensitizedUtil;
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

    /**
     *  切点
     */
    @Pointcut(value = "@annotation(io.gitee.chemors.secure.ext.annotations.Desensitization)")
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
        dealNode(obj);
        return obj;
    }

    private void dealList(Object o) throws IllegalAccessException {
        List<Object> list = (List<Object>) o;
        for (Object obj : list) {
            dealNode(obj);
        }
    }

    public void dealNode(Object o) throws IllegalAccessException {
        if (o instanceof List){
            dealList(o);
            return;
        }
        if (o == null){
            return;
        }
        boolean needDepthDeal = sensitiveProp.getDepth() && !StrUtil.isBlankIfStr(sensitiveProp.getPackages());
        Field[] fields = o.getClass().getDeclaredFields();
        for (Field field : fields){
            field.setAccessible(true);
            String type = field.getGenericType().toString();
            Object fieldValueObj = field.get(o);
            if (fieldValueObj instanceof List){
                dealList(fieldValueObj);
            }
            // 递归子属性
            if (needDepthDeal && containType(type)){
                dealNode(fieldValueObj);
            }

            DesensitizationProp desensitizationProp = field.getAnnotation(DesensitizationProp.class);
            if (desensitizationProp == null) {
                continue;
            }
            String v = MosDesensitizedUtil.desensitizeData(fieldValueObj,desensitizationProp);
            field.set(o, v);
        }
    }

    private Boolean containType(String type){
        String[] scanPackages = sensitiveProp.getPackages().split(",");
        boolean isContainType = false;
        for (String scanPackage : scanPackages){
            if (type.contains(scanPackage)){
                isContainType = true;
                break;
            }
        }
        return isContainType;
    }


}

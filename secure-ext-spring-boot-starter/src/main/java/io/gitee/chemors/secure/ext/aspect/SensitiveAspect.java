/*
 * Copyright (c)  小尘哥. 2022-2024. All rights reserved.
 */

package io.gitee.chemors.secure.ext.aspect;

import io.gitee.chemors.secure.ext.annotations.Desensitization;
import io.gitee.chemors.secure.ext.config.Constants;
import io.gitee.chemors.secure.ext.config.SensitiveProp;
import io.gitee.chemors.secure.ext.util.FieldSensitiveUtil;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.lang.reflect.Method;

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
     * 切点
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
        if (!sensitiveProp.getEnable()) {
            return joinPoint.proceed();
        }
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        Desensitization desensitization = method.getAnnotation(Desensitization.class);
        if (desensitization == null || !desensitization.enable()) {
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
        FieldSensitiveUtil.dealNode(obj,sensitiveProp);
        return obj;
    }

}

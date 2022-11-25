package com.chunjae.doctormath.common.logging;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
/**
 * todo 서비스마다 log쓰기 싫어서 임시로 만듬, 나중에 새로 만드세요.
 * */
@Slf4j
@Component
@Aspect
public class LogConfig {

    @Pointcut("execution(* com.chunjae.doctormath..*.*(..))")
    private void cut() {}

    @Before("cut()")
    public void before(JoinPoint joinPoint) {

        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        Method method = methodSignature.getMethod();
        log.trace("method name ==> {}", method.getName());

        Object[] args = joinPoint.getArgs();

        for(Object obj : args) {
            if (obj != null) {
                log.trace("type ==> {}", obj.getClass().getSimpleName());
                log.trace("value ==> {}", obj);
            }
        }
    }

    @AfterReturning(value = "cut()", returning = "obj")
    public void afterReturn(Object obj) {
        log.trace("response ==> {}", obj);
    }
}

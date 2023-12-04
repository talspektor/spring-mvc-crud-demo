package com.luv2code.springboot.thymeleafdemo.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class DemoLoggingAspect {

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.controller.*.*(..))")
    public void forControllerPackage() {
    }

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.service.*.*(..))")
    public void forServicePackage() {
    }

    @Pointcut("execution(* com.luv2code.springboot.thymeleafdemo.dao.*.*(..))")
    public void forDaoPackage() {
    }

    @Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
    public void forAppFlow() {
    }

    @Before("forAppFlow()")
    public void before(JoinPoint joinPoint) {

        String theMethod = joinPoint.getSignature().toShortString();
        log.info("=====>> in @Before: calling method: " + theMethod);

        Object[] args = joinPoint.getArgs();

        for (Object tempArg : args) {
            log.info("=====>> argument: " + tempArg);
        }
    }

    @AfterReturning(
            pointcut="forAppFlow()",
            returning="theResult"
            )
    public void afterReturning(JoinPoint joinPoint, Object theResult) {

        String theMethod = joinPoint.getSignature().toShortString();
        log.info("=====>> in @AfterReturning: from method: " + theMethod);

        log.info("=====>> result: " + theResult);
    }
}

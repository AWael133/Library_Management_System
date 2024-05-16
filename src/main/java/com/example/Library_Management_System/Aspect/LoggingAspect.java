package com.example.Library_Management_System.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class LoggingAspect {

    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Pointcut("within(com.example.Library_Management_System.Service.*)")
    private void allMethodsOnServices(){}

    @Before("allMethodsOnServices()")
    public void beforeService(JoinPoint joinPoint){
        String className = joinPoint.getThis().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("start %s:%s withArgs %s".formatted(className, methodName, Arrays.toString(args)));
    }

    @After("allMethodsOnServices()")
    public void afterService(JoinPoint joinPoint){
        String className = joinPoint.getThis().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.info("finish %s:%s withArgs %s".formatted(className, methodName, Arrays.toString(args)));
    }

    @AfterThrowing(pointcut = "allMethodsOnServices()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String className = joinPoint.getThis().getClass().getName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        logger.error("<<{}.{}() withArgs {} - {}", className, methodName, Arrays.toString(args), exception.getMessage());
    }
}

package com.example.Library_Management_System.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {

    @Before("within(com.example.Library_Management_System.Service)")
    public void beforeService(JoinPoint joinPoint){

    }
}

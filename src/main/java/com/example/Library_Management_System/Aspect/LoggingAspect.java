package com.example.Library_Management_System.Aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;


@Aspect
@Component
public class LoggingAspect {


    Logger logger = LoggerFactory.getLogger(LoggingAspect.class);

    @Before("within(com.example.Library_Management_System.Service.*)")
    public void beforeService(JoinPoint joinPoint){
        logger.info(joinPoint.getKind());
        logger.info(Arrays.toString(joinPoint.getArgs()));
        logger.info(joinPoint.toString());
        logger.info(joinPoint.getThis().toString());
        logger.info(joinPoint.getTarget().toString());
    }
}

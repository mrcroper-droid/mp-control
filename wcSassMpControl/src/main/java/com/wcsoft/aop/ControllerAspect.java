package com.wcsoft.aop;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson2.JSONObject;

@Aspect
@Component
public class ControllerAspect {
	
	private static final Logger logger = LoggerFactory.getLogger(ControllerAspect.class);
	
	@Autowired
	HttpServletRequest httpServletRequest;

	@Pointcut("execution(* com.wcsoft.controller.*.*(*))")
	public void controllerAspect(){}
	 
	@Before("controllerAspect()")
	public void doBefore(JoinPoint joinPoint){
		try {
			logger.info(httpServletRequest.getRequestURL().append(" 入參:").append(JSONObject.toJSONString(joinPoint.getArgs())).toString());
		}catch (Exception e) {
			logger.info(httpServletRequest.getRequestURL().toString());
		}
	}
	
	@AfterReturning(pointcut = "controllerAspect()")
	public void doAfter(JoinPoint joinPoint){
	}
}

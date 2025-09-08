package com.wcsoft.exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.wcsoft.entity.RespBean;
import com.wcsoft.utils.UuidUtils;

@RestControllerAdvice
public class GlobalException {
	
	@ResponseBody
	@ResponseStatus(HttpStatus.OK)
	@ExceptionHandler(value = Exception.class)
	public Object anyExcetion(HttpServletRequest httpServletRequest, Exception exception, HttpServletResponse htpHttpServletResponse) {
		String exceptionId = UuidUtils.getIdString("err");
		exceptionId = exceptionId.substring(6);
		LoggerFactory.getLogger(this.getClass()).error("["+exceptionId+ "]错误原因:" + exception.getMessage());;
		exception.printStackTrace();
		return RespBean.error(exceptionId, "["+exceptionId+ "] "+exception.getMessage());
	}
	
}

package com.wcsoft.service.impl;

import org.mybatis.spring.SqlSessionTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcsoft.service.AppMenuService;
import com.wcsoft.service.UsersService;
import com.wcsoft.service.WxService;

public abstract class BaseAbstractSevice  {
	@Autowired
	protected SqlSessionTemplate sqlSession;
	@Autowired
	protected UsersService usersService;
	@Autowired
	@Lazy
	protected WxService wxService;
	@Autowired
	protected AppMenuService appMenuService;
	
	@Value("${default.filePath}")
    protected String filePath;
    
	@Value("${default.fileUrl}")
    protected String fileUrl;
	
    protected static ObjectMapper imapper = new ObjectMapper();
	
	public static Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}
		
	
}

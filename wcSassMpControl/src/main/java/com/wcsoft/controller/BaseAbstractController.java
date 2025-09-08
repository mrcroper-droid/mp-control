package com.wcsoft.controller;

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.context.SecurityContextHolder;

import com.alibaba.fastjson2.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcsoft.entity.AppMenu;
import com.wcsoft.exception.RoperRuntimeException;
import com.wcsoft.service.AppMenuService;
import com.wcsoft.service.AppService;
import com.wcsoft.service.GroupService;
import com.wcsoft.service.ProductService;
import com.wcsoft.service.UsersService;
import com.wcsoft.service.WxService;

public abstract class BaseAbstractController {

	@Autowired
	protected UsersService usersService;
	
	@Autowired
	protected ProductService productService;

	@Autowired
	protected GroupService groupService;
	
	@Autowired
	protected WxService wxService;
	
	@Autowired
	protected AppService appService;
	
	@Autowired
	protected AppMenuService appMenuService;
	
    @Value("${default.filePath}")
    protected String filePath;
    @Value("${default.fileUrl}")
    protected String fileUrl;
	
    protected static Logger getLogger(Class<?> clazz) {
		return LoggerFactory.getLogger(clazz);
	}
	protected static ObjectMapper imapper = new ObjectMapper();
	
	public static String getUserId() {
		JSONObject job =  JSONObject.parseObject((String)SecurityContextHolder.getContext().getAuthentication().getPrincipal());
		if(job !=null) {
			return job.getString("id")==null?job.getString("userId"):job.getString("id");
		}
		throw new RoperRuntimeException("用戶信息获取失败");
	}
	
	
	public static void buildUserParam(Map<String, Object> params) {
		params.put("userId", getUserId());
	}
}

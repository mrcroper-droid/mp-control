package com.wcsoft.controller;


import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.wcsoft.entity.RespBean;
import com.wcsoft.entity.WxXml;
import com.wcsoft.entity.WxXmlRes;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Roper
 * @since 2025-01-11
 */
@RestController
@RequestMapping("/app")
public class AppController extends BaseAbstractController{
	
	@PostMapping("addApp")
	public RespBean addApp(@RequestBody Map<String, Object> params) {
		appService.addApp(params);
		return RespBean.ok("插入成功");
	}
	@PostMapping("updApp")
	public RespBean updApp(@RequestBody Map<String, Object> params) {
		appService.updApp(params);
		return RespBean.ok("更新成功");
		
	}
	@PostMapping("delApp")
	public RespBean delApp(@RequestBody Map<String, Object> params) {
		appService.delApp((String)params.get("id"));
		return RespBean.ok("删除成功");
		
	}
	@PostMapping("queryApp")
	public RespBean queryApp(@RequestBody Map<String, Object> params) {
		return RespBean.ok("查询成功", appService.appList(params));
	}
	
	@PostMapping("addAll")
	public RespBean addAll(@RequestBody Map<String, Object> params) {
		String userId = getUserId();
		if(StringUtils.isNotBlank(userId)) {
			params.put("userId", userId);
			appService.addAll(params);
			return RespBean.ok("插入成功");
		}
		return RespBean.error("用户未登录");
			
	}
	@PostMapping("queryAll")
	public RespBean queryAll(@RequestBody Map<String, Object> params) {
		String userId = getUserId();
		if(StringUtils.isNotBlank(userId)) {
			params.put("userId", userId);
			return RespBean.ok("查询成功", appService.queryAll(params));
		}
		return RespBean.error("用户未登录");
	}
	
	@GetMapping("/wx")
	public String wxCheck(@RequestParam("echostr") String echostr) {
		return echostr;
	}
	
	@PostMapping(value = "/wx", consumes = MediaType.TEXT_XML_VALUE, produces = MediaType.TEXT_XML_VALUE)
	public String reply(@RequestBody WxXml wxXml) {
		Map<String, Object> params = new HashMap<>();
		params.put("originAppId", wxXml.getToUserName());
		params.put("menuKey", wxXml.getEventKey());
		params.put("event", wxXml.getEvent());
		params.put("fromUserName", wxXml.getFromUserName());
		String reply = appMenuService.getReplyMsg(params);
		WxXmlRes wxXmlRes = new WxXmlRes();
		wxXmlRes.setFromUserName(wxXml.getToUserName());
		wxXmlRes.setToUserName(wxXml.getFromUserName());
		wxXmlRes.setMsgType("text");
		wxXmlRes.setCreateTime(wxXml.getCreateTime());
		wxXmlRes.setContent(reply);
		return wxXmlRes.toXmlString();
	}	
}

package com.wcsoft.controller;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcsoft.entity.RespBean;
import com.wcsoft.utils.Constants;

@RestController
@RequestMapping("/group")
public class GroupController extends BaseAbstractController{
	
	@PostMapping("/addGroup")
	public RespBean addGroup(@RequestBody Map<String, Object> params) {
		groupService.addGroup(params);
		return RespBean.ok(Constants.P_OK);
	}
	@PostMapping("/editGroup")
	public RespBean editGroup(@RequestBody Map<String, Object> params) {
		groupService.editGroup(params);
		return RespBean.ok(Constants.P_OK);
	}
	@PostMapping("/delGroup")
	public RespBean delGroup(@RequestBody Map<String, Object> params) {
		groupService.delGroup(MapUtils.getString(params, "groupId"));
		return RespBean.ok(Constants.P_OK);
	}
	
	@PostMapping("/allGroup")
	public RespBean allGroup(@RequestBody Map<String, Object> params) {
		
		return RespBean.ok(Constants.Q_OK, groupService.groupArr(params));
	}
	
	
}

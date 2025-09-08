package com.wcsoft.controller;


import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import com.wcsoft.entity.RespBean;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Roper
 * @since 2025-01-11
 */
@RestController
@RequestMapping("/appMenu")
public class AppMenuController extends BaseAbstractController{

	@PostMapping("addMenuApp")
	public RespBean addApp(@RequestBody List<Map<String, Object>> arr) {
		for(Map<String, Object> params: arr) {
			appMenuService.addAppMenu(params);
		}
		return RespBean.ok("插入成功");
			
	}
	@PostMapping("updAppMenu")
	public RespBean updApp(@RequestBody Map<String, Object> params) {
		appMenuService.updAppMenu(params);
		return RespBean.ok("更新成功");
		
	}
	@PostMapping("delAppMenu")
	public RespBean delApp(@RequestBody Map<String, Object> params) {
		appMenuService.delAppMenu((String)params.get("id"));
		return RespBean.ok("删除成功");
		
	}
	@PostMapping("queryAppMenu")
	public RespBean queryApp(@RequestBody Map<String, Object> params) {
		return RespBean.ok("查询成功", appMenuService.appMenuList(params));
	}
	@PostMapping("setMenuNow")
	public RespBean setMenuNow(@RequestBody Map<String, Object> params) {
		appMenuService.setMenuNow(params);
		return RespBean.ok("更新菜单成功");
	}
}

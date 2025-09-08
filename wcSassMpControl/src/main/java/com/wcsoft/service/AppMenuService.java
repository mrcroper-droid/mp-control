package com.wcsoft.service;

import java.util.List;
import java.util.Map;

import com.wcsoft.entity.AppMenu;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Roper
 * @since 2025-01-11
 */
public interface AppMenuService  {

	void addAppMenu(Map<String, Object> params);
	
	void updAppMenu(Map<String, Object> params);
	
	void delAppMenu(String id);
	
	List<AppMenu> appMenuList(Map<String, Object> params);
	
	String getReplyMsg(Map<String, Object> params);
	
	void setMenuNow(Map<String, Object> params);
}

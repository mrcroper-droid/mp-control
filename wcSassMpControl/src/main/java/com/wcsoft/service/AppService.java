package com.wcsoft.service;

import java.util.List;
import java.util.Map;

import com.wcsoft.entity.App;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Roper
 * @since 2025-01-11
 */
public interface AppService  {
	
	void addApp(Map<String, Object> params);
	
	void updApp(Map<String, Object> params);
	
	void delApp(String id);
	
	List<App> appList(Map<String, Object> params);
	
	void addAll(Map<String, Object> params);

	List<Map<String, Object>> queryAll(Map<String, Object> params);
}

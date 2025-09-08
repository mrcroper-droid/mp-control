package com.wcsoft.service;

import java.util.List;
import java.util.Map;

import com.github.pagehelper.PageInfo;
import com.wcsoft.entity.Users;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Roper
 * @since 2023-10-11
 */
public interface UsersService  {

	PageInfo<List<Map<String, Object>>> userInfoQuery(Map<String, Object> params);
	
	Users createUser(Map<String, Object> params);
	
	String queryCurMostLevel(String level);
	/**
	 * 登录时获取的 code，可通过wx.login获取
	 * @param jsCode
	 * @return
	 */
	Object wechUserLogin(String jsCode);
	
	List<?> myUsers(Map<String, Object> params);
	
	void updateUsers(Map<String, Object> params);
	
	void bindUsers(String userId, String bindUserId);
	
	Object genUsersQrCode(String userId, String type);
}

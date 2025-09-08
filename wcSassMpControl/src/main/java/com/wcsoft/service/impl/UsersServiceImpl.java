package com.wcsoft.service.impl;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson2.JSONObject;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.wcsoft.entity.JwtUser;
import com.wcsoft.entity.Users;
import com.wcsoft.exception.RoperRuntimeException;
import com.wcsoft.service.UsersService;
import com.wcsoft.utils.Utils;
import com.wcsoft.utils.UuidUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Roper
 * @since 2023-10-11
 */
@Service
public class UsersServiceImpl extends BaseAbstractSevice implements UsersService, UserDetailsService {

	@Override
	public PageInfo<List<Map<String, Object>>> userInfoQuery(Map<String, Object> params) {
		PageHelper.startPage(params);
		List<Map<String, Object>> usersArr = this.sqlSession.selectList("com.wcsoft.mapper.UsersMapper.selectAllMap");
		PageInfo<List<Map<String, Object>>> pageInfo =new PageInfo(usersArr);
		return pageInfo;
	}

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Users user =this.sqlSession.selectOne("com.wcsoft.mapper.UsersMapper.selectByUsername", username);
		if (user == null) {
            throw new UsernameNotFoundException("登录用户：" + username + "不存在");
        }
        JwtUser jwtUser = new JwtUser(user);
        return jwtUser;
	}

	@Override
	public Users createUser(Map<String, Object> params) {
		Users users = new Users();
		users.setUserId(UuidUtils.getIdString("u"));
		users.setUsername(Utils.null2Empty(params.get("username")));
		users.setName(Utils.null2Empty(params.get("username")));
		users.setPassword(Utils.null2Empty(params.get("password")));
		users.setRoleId("1");
		users.setCreateTime(LocalDateTime.now());
		users.setStatus("0");
		Users tmpUser = this.sqlSession.selectOne("com.wcsoft.mapper.UsersMapper.selectByUsername", users.getUsername());
		if(tmpUser!=null) {
			throw new RoperRuntimeException("用户名userName已存在，请更换");
		}
		
		this.sqlSession.insert("com.wcsoft.mapper.UsersMapper.insert", users);
		return users;
	}

	@Override
	public String queryCurMostLevel(String level) {
		Map<String, Object> qryParams = new HashMap<>();
		qryParams.put("fristLike", level+ ".%");
		qryParams.put("secondLike", level+ ".%"+ ".%");
		String mostLevel = this.sqlSession.selectOne("com.wcsoft.mapper.UsersMapper.selectLevel", qryParams);
		return mostLevel;
	}

	@Override
	public Object wechUserLogin(String jsCode) {
		return jsCode;
//		JSONObject job = wechatHandel.code2Seesion(jsCode);
//		String openId = job.getString("openid");
//		Users users= this.sqlSession.selectOne("com.wcsoft.mapper.UsersMapper.selectByOpenId", openId);
//		if(users!=null) {
//			return users;	
//		}
//		return openId;
	}

	@Override
	public List<?> myUsers(Map<String, Object> params) {
		String flag = Utils.null2Empty(params.get("flag"));
		Users user = this.sqlSession.selectOne("com.wcsoft.mapper.UsersMapper.selectByPrimaryKey", params.get("userId"));
		PageHelper.startPage(params);
		List<Users> myUsersArr = this.sqlSession.selectList("com.wcsoft.mapper.UsersMapper.selectUserByLevel", user.getLevel() + ".%");
		List<String> userIdArr = myUsersArr.stream().map(users->users.getUserId()).collect(Collectors.toList());
//		userIdArr.add(user.getUserId());
		switch (flag) {
		case "2":
			return myUsersArr;
		case "1"://1代理
			return userIdArr;
		case "0"://0用户
			Map<String, Object> qryParams = new HashMap<>();
			qryParams.put("userIdArr", userIdArr);
			myUsersArr = this.sqlSession.selectList("com.wcsoft.mapper.UsersMapper.selectByAnyKey", qryParams); 
			userIdArr = myUsersArr.stream().map(users->users.getUserId()).collect(Collectors.toList());
			return userIdArr;
		case "3":
			Map<String, Object> a = new HashMap<>();
			a.put("userIdArr", userIdArr);
			myUsersArr = this.sqlSession.selectList("com.wcsoft.mapper.UsersMapper.selectByAnyKey", a); 
			return myUsersArr;
		}
		return null;
	}

	@Override
	public void updateUsers(Map<String, Object> params) {
		this.sqlSession.update("com.wcsoft.mapper.UsersMapper.updateNameOrPassword", params);
		
	}

	@Override
	public void bindUsers(String userId, String bindUserId) {
		Users bindUser = this.sqlSession.selectOne("com.wcsoft.mapper.UsersMapper.selectByPrimaryKey", bindUserId);
		Users wxUser =  this.sqlSession.selectOne("com.wcsoft.mapper.UsersMapper.selectByPrimaryKey", userId);
		this.sqlSession.delete("com.wcsoft.mapper.UsersMapper.deleteByPrimaryKey", userId);
		Map<String, Object> params =new HashMap<>();
		params.put("newUserId", bindUserId);
		params.put("oldUserId", userId);
		this.sqlSession.update("com.wcsoft.mapper.ContactsMapper.updateUserId", params);
		this.sqlSession.update("com.wcsoft.mapper.OrderMapper.updateUserId", params);
		bindUser.setOpenId(wxUser.getOpenId());
		bindUser.setUuid(wxUser.getUuid());
		bindUser.setModifyTime(LocalDateTime.now());
		this.sqlSession.update("com.wcsoft.mapper.UsersMapper.updateByPrimaryKey", bindUser);
	}

	@Override
	public Object genUsersQrCode(String userId, String type) {
		return type;
//		return wechatHandel.getQrCode(userId, type, sqlSession);
	}


}

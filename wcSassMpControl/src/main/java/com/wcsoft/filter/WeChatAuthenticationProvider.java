//package com.wcsoft.filter;
//
//import java.util.ArrayList;
//import java.util.HashMap;
//import java.util.List;
//import java.util.Map;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//
//import com.alibaba.fastjson2.JSONObject;
//import com.wcsoft.entity.Users;
//import com.wcsoft.service.CourierService;
//import com.wcsoft.service.UsersService;
//import com.wcsoft.utils.Utils;
//
//public class WeChatAuthenticationProvider implements AuthenticationProvider {
// 
//	@Autowired
//	private UsersService usersService;
//	
//	@Autowired
//	private CourierService courierService;
// 
//    @Override
//    public Authentication authenticate(Authentication authentication) {
// 
//        if (authentication.isAuthenticated()) {
//            return authentication;
//        }
//        //获取过滤器封装的token信息
//        WeChatAuthenticationToken authenticationToken = (WeChatAuthenticationToken) authentication;
//        JSONObject job = (JSONObject)authenticationToken.getPrincipal();
//        Object obj = usersService.wechUserLogin(job.getString("jscode"));
//        Users users = null;
//        if(obj instanceof String) {
//        	Map<String, Object> newUsers = new HashMap<>();
//        	newUsers.put("username", job.getString("jscode").substring(16));
//        	newUsers.put("roleId", "4");
//        	newUsers.put("openId", obj);
//        	newUsers.put("promotionId", job.getString("promotionId"));
//        	users = usersService.createUser(newUsers);
//        }else {
//        	users = (Users)obj;
//        }
//        if(Utils.isNotBlank(job.getString("staffId"))) {
//        	//添加配送员
//        	courierService.bindCourier(users.getUserId(), job.getString("staffId"), users.getOpenId());
//        }
//        if(Utils.isNotBlank(job.getString("bindUserId"))) {
//        	//机构人员绑定openid
//        	usersService.bindUsers(users.getUserId(), job.getString("bindUserId"));
//        }
//        List<GrantedAuthority> authorities = new ArrayList<>();
//        GrantedAuthority grantedAuthority=  new SimpleGrantedAuthority("4");
//        authorities.add(grantedAuthority);
//        WeChatAuthenticationToken authenticationResult = new WeChatAuthenticationToken(users, authorities);
//        return authenticationResult;
//    }
// 
//    @Override
//    public boolean supports(Class<?> authentication) {
//    	return WeChatAuthenticationToken.class.isAssignableFrom(authentication);
//    }
// 
//}
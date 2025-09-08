//package com.wcsoft.filter;
//
//import java.io.IOException;
//import java.io.InputStreamReader;
//
//import javax.servlet.FilterChain;
//import javax.servlet.ServletException;
//import javax.servlet.ServletInputStream;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.AuthenticationException;
//import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
//
//import com.alibaba.fastjson2.JSON;
//import com.alibaba.fastjson2.JSONObject;
//import com.google.common.base.Charsets;
//import com.google.common.io.CharStreams;
//import com.wcsoft.entity.RespBean;
//import com.wcsoft.entity.Users;
//import com.wcsoft.utils.Constants;
//import com.wcsoft.utils.JwtTokenUtils;
//
//public class WechAuthenticationFilter extends AbstractAuthenticationProcessingFilter {
//
//	
//
//    public WechAuthenticationFilter() {
//        super("/auth/wechLogin");
//    }
//
//    @Override
//    public Authentication attemptAuthentication(HttpServletRequest request,
//                                                HttpServletResponse response) throws AuthenticationException {
//    	  JSONObject job  = obtainKey(request);
//    	  WeChatAuthenticationToken authRequest = new WeChatAuthenticationToken(job);
//    	  authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
//    	  return this.getAuthenticationManager().authenticate(authRequest);
//    }
//
//    // 成功验证后调用的方法
//    // 如果验证成功，就生成token并返回
//    @Override
//    protected void successfulAuthentication(HttpServletRequest request,
//                                            HttpServletResponse response,
//                                            FilterChain chain,
//                                            Authentication authResult) throws IOException, ServletException {
//    	Users user = (Users) authResult.getPrincipal();
//        String token = JwtTokenUtils.createToken(JSON.toJSONString(user), "4");
//        Users users =new Users();
//        users.setPassword(token);
//        users.setOpenId(user.getOpenId());
//        users.setUsername(user.getUsername());
//        users.setUserId(user.getUserId());
//        users.setRoleId(user.getRoleId());
//        RespBean resp = RespBean.ok(Constants.P_OK, users);
//        response.getOutputStream().write(JSON.toJSONString(resp).getBytes());
//        response.setHeader("token", token);
//    }
//
//    @Override
//    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
//    	RespBean resp = RespBean.error("登录失败,"+failed.getMessage());
//    	response.getWriter().write(JSON.toJSONString(resp));
//    }
//    
//    protected JSONObject obtainKey(HttpServletRequest request) {
//        try {
//			ServletInputStream inputStream= request.getInputStream();
//		    String s = CharStreams.toString(new InputStreamReader(inputStream, Charsets.UTF_8));
//            JSONObject jsonObject = JSON.parseObject(s);
//            return jsonObject;
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//        return null;
//    }
//}
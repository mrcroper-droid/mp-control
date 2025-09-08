package com.wcsoft.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcsoft.entity.RespBean;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAuthenticationEntryPoint implements AuthenticationEntryPoint {
    @Override
    public void commence(HttpServletRequest request,
                         HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {

        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        RespBean resp = RespBean.error("无权访问"+ authException.getMessage());
        response.getWriter().write(new ObjectMapper().writeValueAsString(resp));
    }
}

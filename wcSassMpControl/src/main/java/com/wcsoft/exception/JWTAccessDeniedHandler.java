package com.wcsoft.exception;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wcsoft.entity.RespBean;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class JWTAccessDeniedHandler implements AccessDeniedHandler {
    @Override
    public void handle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AccessDeniedException e) throws IOException, ServletException {
        httpServletResponse.setCharacterEncoding("UTF-8");
        httpServletResponse.setContentType("application/json; charset=utf-8");
        httpServletResponse.setStatus(HttpServletResponse.SC_FORBIDDEN);
        RespBean resp = RespBean.error("无权访问"+ e.getMessage());
        httpServletResponse.getWriter().write(new ObjectMapper().writeValueAsString(resp));
    }
}

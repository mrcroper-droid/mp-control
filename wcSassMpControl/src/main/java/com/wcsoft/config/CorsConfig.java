package com.wcsoft.config;

import java.io.IOException;
import java.util.Enumeration;
import java.util.Objects;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author: theTian
 * @date: 2020/5/28 0:08
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer,Filter {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("GET", "HEAD", "POST", "PUT", "DELETE", "OPTIONS")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedHeaders("*");
    }
    private CorsConfiguration buildConfig() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*"); //允许任何域名
        corsConfiguration.addAllowedHeader("*"); //允许任何头
        corsConfiguration.addAllowedMethod("*"); //允许任何方法
        return corsConfiguration;
    }

    @Bean
    public CorsFilter corsFilter() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); //注册
        return new CorsFilter(source);
    }
    @Override
    public void doFilter(ServletRequest arg0, ServletResponse arg1, FilterChain arg2)
            throws IOException, ServletException {
        HttpServletResponse response = (HttpServletResponse) arg1;
        response.setHeader("Access-Control-Allow-Origin","*");
        response.setHeader("Access-Control-Allow-Credentials", "true");
        response.setHeader("Access-Control-Allow-Methods", "*");
        response.setHeader("Access-Control-Max-Age", "3600");
        response.setHeader("Access-Control-Allow-Headers", "*");
        response.setHeader("Access-Control-Request-Headers", "*");
        response.setHeader("Access-Control-Expose-Headers", "*");
        HttpServletRequest request=(HttpServletRequest)arg0;
        StringBuilder headers = new StringBuilder();
        Enumeration<String> headerNames = request.getHeaders("Access-Control-Request-Headers");
        if(Objects.nonNull(headerNames)) {
        	while(headerNames.hasMoreElements()) {
        		headers.append(headerNames.nextElement()).append(",");
        	}
        }
        response.setHeader("Access-Control-Allow-Headers", headers.toString());
        if(HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
        	response.setStatus(HttpStatus.NO_CONTENT.value());
        }else {
        	arg2.doFilter(arg0,arg1);
        }
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("").addResourceLocations("file:" + "");
    }
}

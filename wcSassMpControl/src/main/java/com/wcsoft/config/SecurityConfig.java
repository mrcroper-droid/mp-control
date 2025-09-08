package com.wcsoft.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.wcsoft.exception.JWTAccessDeniedHandler;
import com.wcsoft.exception.JWTAuthenticationEntryPoint;
import com.wcsoft.filter.JWTAuthenticationFilter;
import com.wcsoft.filter.JWTAuthorizationFilter;

@EnableWebSecurity
//开启注解配置权限
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    @Qualifier("usersServiceImpl")
    private UserDetailsService userDetailsService;

    @Bean  
    public PasswordEncoder passwordEncoder() {  
        return NoOpPasswordEncoder.getInstance();  
    }  
//    @Bean
//    public WeChatAuthenticationProvider weChatAuthenticationProvider() {
//      return new WeChatAuthenticationProvider();
//    }
//    @Bean
//    public WechAuthenticationFilter weChatAuthenticationFilter() throws Exception {
//    	WechAuthenticationFilter filter = new WechAuthenticationFilter();
//      filter.setAuthenticationManager(authenticationManagerBean());
//      return filter;
//    }
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
      //微信openid登陆
//        auth.authenticationProvider(weChatAuthenticationProvider());
    }
    

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.cors().and().csrf().disable()
	        .authorizeRequests()
	//                .antMatchers(HttpMethod.DELETE, "/tasks/**").hasRole("ADMIN")
	        .antMatchers("/auth/**").permitAll()
	        .antMatchers("/users/createUser").permitAll()
	        .antMatchers("/product/queryProductGroup").permitAll()
	        .antMatchers("/group/allGroup").permitAll()
	        .antMatchers("/product/queryDataList").permitAll()
	        .antMatchers("/product/queryProduct").permitAll()
	        .antMatchers("/product/getWxCode").permitAll()
	        .antMatchers("/file/pdfToImg").permitAll()
	        .antMatchers("/mahua/**").permitAll()
	        .antMatchers("/app/wx").permitAll()
	        // 其他都放行了
	        .anyRequest().authenticated()
	        .and()
//	        .addFilterBefore(weChatAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class)
	        .addFilter(new JWTAuthenticationFilter(authenticationManager()))
	        .addFilter(new JWTAuthorizationFilter(authenticationManager()))
	        // 不需要session
	        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
	        .and()
	        .exceptionHandling().authenticationEntryPoint(new JWTAuthenticationEntryPoint())
	        .accessDeniedHandler(new JWTAccessDeniedHandler());      //添加无权限时的处理
    }

    @Bean
    CorsConfigurationSource corsConfigurationSource() {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
        return source;
    }
}

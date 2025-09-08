//package com.wcsoft.filter;
//
//import java.util.Collection;
//
//import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
//import org.springframework.security.core.GrantedAuthority;
//
//public class WeChatAuthenticationToken extends UsernamePasswordAuthenticationToken {
// 
//    private static final long serialVersionUID = -6231962326068951783L;
// 
// 
//    public WeChatAuthenticationToken(Object principal) {
//        super(principal, "");
//    }
// 
// 
//    public WeChatAuthenticationToken(Object principal, Collection<? extends GrantedAuthority> authorities) {
//        super(principal, "", authorities);
//    }
// 
//}
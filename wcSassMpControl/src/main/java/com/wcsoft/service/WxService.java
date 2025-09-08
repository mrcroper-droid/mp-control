package com.wcsoft.service;

public interface WxService {

//	Object wxQrCode(Map<String, Object> params);
	
	void ceateMenu(String appId, String appSecret, String button);
	
	void sendAfterMsg(String appId, String appSecret, String msgJson, int second);
	
	String addMaterial(String appId, String appSecret, String base64img);
}

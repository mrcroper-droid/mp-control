package com.wcsoft.service.impl;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.collections4.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.util.ByteArrayBuffer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONObject;
import com.wcsoft.entity.Product;
import com.wcsoft.exception.RoperRuntimeException;
import com.wcsoft.service.WxService;
import com.wcsoft.utils.HttpUtils;
import com.wcsoft.utils.UuidUtils;

@Service
public class WxServiceImpl extends BaseAbstractSevice implements WxService{

//	@Value("${wx.appId}")
//	private String appId;
//	@Value("${wx.appSecret}")	
//	private String appSecret;
//	@Value("${wx.qrCodeModalPath}")
//	private String qrCodeModalPath;
//	@Value("${wx.qrCodeEnvVer}")
//	private String qrCodeEnvVer;
	
	private String getWxacodeunlimit = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token=%s";
	private String getAccessToken = "https://api.weixin.qq.com/cgi-bin/token";
	private String menuCreate = "https://api.weixin.qq.com/cgi-bin/menu/create?access_token=%s";
	private String customSend = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=%s";
	private String addMaterial = "https://api.weixin.qq.com/cgi-bin/material/add_material?access_token=%s&type=image";
//	@Override
//	public Object wxQrCode(Map<String, Object> params) {
//		/*{
//			 "page": "pages/index/index",
//			 "scene": "a=1",
//			 "check_path": true,
//			 "env_version": "release"
//			}
//		*/
//		String picText = MapUtils.getString(params, "picText");
//		String accessToken = accessToken();
//		Map wxParams = MapUtils.getMap(params, "wxParams");
//		wxParams.put("env_version", qrCodeEnvVer);
//		byte[] img = HttpUtils.sendPost(String.format(getWxacodeunlimit, accessToken), JSON.toJSONString(wxParams));
//		if(img.length<500) {
//			throw new RoperRuntimeException(new String(img));
//		}
//		FileOutputStream fos;
//		String qrCodePath = filePath+picText;
//		String qrCodeFileResutPath = filePath +"r"+picText+".png";
//		try {
//			fos = new FileOutputStream(new File(qrCodePath));
//			fos.write(img);
//			fos.close();
//			BufferedImage banner = ImageIO.read(new File(qrCodeModalPath));
//			BufferedImage code = ImageIO.read(new File(qrCodePath));
//			Graphics2D g = banner.createGraphics();
//			g.setColor(Color.black);
//			g.setFont(new Font("宋体", Font.BOLD, 88));
//			g.drawImage(code, 635, 635, 315, 315, null);
//			g.drawString(picText, 75, 190);
//			g.dispose();
//            ImageIO.write(banner, "png", new File(qrCodeFileResutPath));
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			throw new RoperRuntimeException("图片生成失败");
//		}
//		return qrCodeFileResutPath.replace(filePath, fileUrl);
//	}

	public String accessToken(String appId, String appSecret) {
		Map<String, Object> params = new HashMap<>();
		params.put("grant_type", "client_credential");
		params.put("appid", appId);
		params.put("secret", appSecret);
		String responseStr = HttpUtils.doGet(getAccessToken, params);
		String accessToken = JSONObject.parseObject(responseStr).getString("access_token");
		if(StringUtils.isBlank(accessToken)) {
			throw new RuntimeException(responseStr);
		}
		return accessToken;
	}

	@Override
	public void ceateMenu(String appId, String appSecret, String button) {
		String accessToken = accessToken(appId, appSecret);
		String url = String.format(menuCreate, accessToken);
		String res = HttpUtils.sendPost(url, button);
		Integer errcode = JSONObject.parseObject(res).getInteger("errcode");
		if(0!=errcode) {
			throw new RuntimeException(res);
		}
	}


	@Override
	@Async
	public void sendAfterMsg(String appId, String appSecret, String msgJson, int second) {
		try {
			Thread.sleep(second*1000l);
			String accessToken = accessToken(appId, appSecret);
			String url = String.format(customSend, accessToken);
			String res = HttpUtils.sendPost(url, msgJson);
			Integer errcode = JSONObject.parseObject(res).getInteger("errcode");
			if(0!=errcode) {
				throw new RuntimeException(res);
			}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	@Override
	public String addMaterial(String appId, String appSecret, String base64img) {
		String accessToken = accessToken(appId, appSecret);
		String url = String.format(addMaterial, accessToken);
		try {
			String res = HttpUtils.postBase64File(url, "media", base64img, "multipart/form-data", UuidUtils.getId()+".png");
			Integer errcode = JSONObject.parseObject(res).getInteger("errcode");
			if(errcode!=null&&0!=errcode) {
				throw new RuntimeException(res);
			}
			String mediaId = JSONObject.parseObject(res).getString("media_id");
			return mediaId;
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("图片上传失败");
		}
	}
	public static void main(String[] args) {
		String base64img = "iVBORw0KGgoAAAANSUhEUgAAADIAAAAZAQMAAABJmu8HAAAAA1BMVEXr6+uInxNMAAAAC0lEQVQI12MYJgAAAMgAAeVBJwAAAAAASUVORK5CYII=";
		WxServiceImpl wxServiceImpl = new WxServiceImpl();
		System.out.println(wxServiceImpl.addMaterial("wx490c4479443557a0", "2f5f85b10d64ef4a9800f7929e6c0a49", base64img));
	}
}

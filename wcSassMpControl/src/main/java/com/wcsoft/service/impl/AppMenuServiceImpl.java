package com.wcsoft.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson2.JSONArray;
import com.alibaba.fastjson2.JSONObject;
import com.wcsoft.entity.App;
import com.wcsoft.entity.AppMenu;
import com.wcsoft.entity.AppReply;
import com.wcsoft.service.AppMenuService;
import com.wcsoft.utils.UuidUtils;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Roper
 * @since 2025-01-11
 */
@Service
public class AppMenuServiceImpl extends BaseAbstractSevice implements AppMenuService {
	Logger logger = getLogger(this.getClass());
	@Override
	public void addAppMenu(Map<String, Object> params) {
		params.put("id", UuidUtils.getId().toString());
//		params.put("menuType", "click");
		params.put("menuKey", "k"+UuidUtils.getId());
		sqlSession.insert("com.wcsoft.mapper.AppMenuMapper.insert", params);
		
	}

	@Override
	public void updAppMenu(Map<String, Object> params) {
		AppMenu app = imapper.convertValue(params, AppMenu.class);
		sqlSession.update("com.wcsoft.mapper.AppMenuMapper.updateByPrimaryKey", app);
	}

	@Override
	public void delAppMenu(String id) {
		sqlSession.delete("com.wcsoft.mapper.AppMenuMapper.deleteByPrimaryKey", id);
		
	}

	@Override
	public List<AppMenu> appMenuList(Map<String, Object> params) {
		return sqlSession.selectList("com.wcsoft.mapper.AppMenuMapper.selectByAnyKey", params);
	}

	@Override
	public String getReplyMsg(Map<String, Object> params) {
		Map<String, Object> temMap = new HashMap<>();
		temMap.put("originAppId", params.get("originAppId"));
		App app = this.sqlSession.selectOne("com.wcsoft.mapper.AppMapper.selectByAnyKey", temMap);
		if("subscribe".equals(params.get("event"))) {
			List<AppReply> appReplyArr = this.sqlSession.selectList("com.wcsoft.mapper.AppReplyMapper.selectByAppId", app.getAppId());
			for(AppReply appReply:appReplyArr) {
				JSONObject msgJson = new JSONObject();
				msgJson.put("touser", params.get("fromUserName"));
				msgJson.put("msgtype", appReply.getMsgType());
				JSONObject job = new JSONObject();
				switch (appReply.getMsgType()) {
				case "text":
					job.put("content", appReply.getContent());
					break;
				case "miniprogrampage":
					job.put("title", appReply.getTitle());
					job.put("appid", appReply.getAppIdDes());
					job.put("pagepath", appReply.getPagePath());
					job.put("thumb_media_id", appReply.getThumbMediaId());
					break;
				case "image":
					job.put("media_id", appReply.getMediaId());
					break;
				}
				msgJson.put(appReply.getMsgType(), job);
				String msgJsonStr = JSONObject.toJSONString(msgJson);
				wxService.sendAfterMsg(app.getAppId(), app.getAppSecret(), msgJsonStr, app.getSecondReplyTime());
			}
			return app.getFollowReply();
		}
		String appId = app.getAppId();
		params.put("appId", appId);
		AppMenu appMenu = this.sqlSession.selectOne("com.wcsoft.mapper.AppMenuMapper.selectByAnyKey", params);
		String content = appMenu.getContent();
		logger.info(content);
		return content;
	}

	@Override
	public void setMenuNow(Map<String, Object> params) {
		List<AppMenu> appMenuArr = this.sqlSession.selectList("com.wcsoft.mapper.AppMenuMapper.selectByAnyKey", params);
		Map<String, Object> temMap = new HashMap<>();
		temMap.put("appId", params.get("appId"));
		App app = this.sqlSession.selectOne("com.wcsoft.mapper.AppMapper.selectByAnyKey", temMap);
		JSONObject buttonJson = new JSONObject();
		JSONArray button =new JSONArray();
		for(AppMenu appMenu: appMenuArr) {
			JSONObject job = new JSONObject();
			job.put("type", appMenu.getMenuType());
			job.put("name", appMenu.getMenuName());
			if(StringUtils.isNoneBlank(appMenu.getMenuKey())) {
				job.put("key", appMenu.getMenuKey());
			}
			if(StringUtils.isNoneBlank(appMenu.getAppIdDes())) {
				job.put("appid", appMenu.getAppIdDes());
				job.put("pagepath", appMenu.getPagePath());
				job.put("url", "http://mp.weixin.qq.com");
			}
			if(StringUtils.isNoneBlank(appMenu.getUrl())) {
				job.put("url", appMenu.getUrl());
			}
			button.add(job);
		}
		buttonJson.put("button", button);
		String json = JSONObject.toJSONString(buttonJson);
		logger.info(json);
		wxService.ceateMenu(app.getAppId(), app.getAppSecret(), json);
	}

}

package com.wcsoft.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wcsoft.entity.App;
import com.wcsoft.entity.AppMenu;
import com.wcsoft.entity.AppReply;
import com.wcsoft.service.AppService;
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
public class AppServiceImpl extends BaseAbstractSevice implements AppService {
	
	@Override
	public void addApp(Map<String, Object> params) {
		params.put("id", UuidUtils.getId().toString());
		sqlSession.insert("com.wcsoft.mapper.AppMapper.insert", params);
	}

	@Override
	public void updApp(Map<String, Object> params) {
		App app = imapper.convertValue(params, App.class);
		sqlSession.update("com.wcsoft.mapper.AppMapper.updateByPrimaryKey", app);
	}

	@Override
	public void delApp(String id) {
		this.sqlSession.delete("com.wcsoft.mapper.AppMapper.deleteByAppId", id);
		this.sqlSession.delete("com.wcsoft.mapper.AppMenuMapper.deleteByAppId", id);
		this.sqlSession.delete("com.wcsoft.mapper.AppReplyMapper.deleteByAppId", id);
	}

	@Override
	public List<App> appList(Map<String, Object> params) {
		return sqlSession.selectList("com.wcsoft.mapper.AppMapper.selectByAnyKey", params);
	}

	@Override
	@Transactional
	public void addAll(Map<String, Object> params) {
		this.sqlSession.delete("com.wcsoft.mapper.AppMapper.deleteByAppId", params);
		this.sqlSession.delete("com.wcsoft.mapper.AppMenuMapper.deleteByAppId", params);
		this.sqlSession.delete("com.wcsoft.mapper.AppReplyMapper.deleteByAppId", params);
		addApp(params);
		List<Map<String, Object>> appMenus = (List<Map<String, Object>>)params.get("appMenu");
		for(Map<String, Object> appMenu: appMenus) {
			appMenu.put("userId", params.get("userId"));
			appMenuService.addAppMenu(appMenu);
		}
		Object appReplyObj = params.get("appReply");
		if(appReplyObj!=null) {
			Map<String, Object> appReply = (Map<String, Object>)appReplyObj;
			if(!appReply.isEmpty()) {
				if("image".equals(appReply.get("msgType"))) {
					String media = (String)appReply.get("media");
					if(StringUtils.isNotBlank(media)) {
						String mediaId = wxService.addMaterial((String)params.get("appId"), (String)params.get("appSecret"), media);
						appReply.put("mediaId", mediaId);
					}
				}
				if("miniprogrampage".equals(appReply.get("msgType"))) {
					String thumbMedia = (String)appReply.get("thumbMedia");
					if(StringUtils.isNotBlank(thumbMedia)) {
						String thumbMediaId = wxService.addMaterial((String)params.get("appId"), (String)params.get("appSecret"), thumbMedia);
						appReply.put("thumbMediaId", thumbMediaId);
					}
				}
				appReply.put("id", UuidUtils.getId().toString());
				appReply.put("appId", params.get("appId"));
				this.sqlSession.insert("com.wcsoft.mapper.AppReplyMapper.insert", appReply);
			}
		}
		appMenuService.setMenuNow(params);
	}

	@Override
	public List<Map<String, Object>> queryAll(Map<String, Object> params) {
		List<App> apps = appList(params);
		List<Map<String, Object>> result = new ArrayList<>();
		for(App app:apps) {
			params.put("appId", app.getAppId());
			List<AppMenu> appMenus = appMenuService.appMenuList(params);
			List<AppReply> appReplys = this.sqlSession.selectList("com.wcsoft.mapper.AppReplyMapper.selectByAppId", app.getAppId());
			Map<String, Object> appMap = imapper.convertValue(app, Map.class);
			appMap.put("appMenu", appMenus);
			if(CollectionUtils.isNotEmpty(appReplys)) {
				AppReply appReply = appReplys.get(0);
				appMap.put("appReply", appReply);
			}else {
				appMap.put("appReply", new HashMap<>());
			}
			result.add(appMap);
		}
		return result;
	}

}

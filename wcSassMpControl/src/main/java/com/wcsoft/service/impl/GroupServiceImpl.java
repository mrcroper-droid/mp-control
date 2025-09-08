package com.wcsoft.service.impl;

import java.util.List;
import java.util.Map;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;

import com.wcsoft.entity.Group;
import com.wcsoft.service.GroupService;
import com.wcsoft.utils.DateUtils;
import com.wcsoft.utils.UuidUtils;

@Service
public class GroupServiceImpl extends BaseAbstractSevice implements GroupService{

	@Override
	@CacheEvict(cacheNames = "productDataList")
	public void addGroup(Map<String, Object> params) {
		params.put("groupId", UuidUtils.getIdString("g"));
		params.put("createDatetime", DateUtils.getCurrentDateTime());
		this.sqlSession.insert("com.wcsoft.mapper.GroupMapper.insert", params);
	}

	@Override
	@CacheEvict(cacheNames = "productDataList")
	public void editGroup(Map<String, Object> params) {
		this.sqlSession.update("com.wcsoft.mapper.GroupMapper.updateByPrimaryKey", params);
	}

	@Override
	@CacheEvict(cacheNames = "productDataList")
	public void delGroup(String groupId) {
		this.sqlSession.delete("com.wcsoft.mapper.GroupMapper.deleteByPrimaryKey", groupId);
	}

	@Override
	public List<Group> groupArr(Map<String, Object> params) {
		return this.sqlSession.selectList("com.wcsoft.mapper.GroupMapper.selectByAnyKey", params);
	}

}

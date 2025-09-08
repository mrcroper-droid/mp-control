package com.wcsoft.service;

import java.util.List;
import java.util.Map;

import com.wcsoft.entity.Group;

public interface GroupService {

	public void addGroup(Map<String, Object> params);
	
	public void editGroup(Map<String, Object> params);
	
	public void delGroup(String groupId);
	
	public List<Group> groupArr(Map<String, Object> params);
}

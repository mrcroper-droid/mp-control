package com.wcsoft.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.collections4.CollectionUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.wcsoft.entity.Group;
import com.wcsoft.entity.Product;
import com.wcsoft.service.ProductService;
import com.wcsoft.utils.UuidUtils;

@Service
public class ProductServiceImpl extends BaseAbstractSevice implements ProductService{
	@Override
	@Cacheable(cacheNames = "productDataList")
	public List<Map<String, Object>> queryDataList(Map<String, Object> params) {
		List<Map<String, Object>> result = new ArrayList<>();
		List<Group> groupArr = this.sqlSession.selectList("com.wcsoft.mapper.GroupMapper.selectAll");
		for(Group group: groupArr) {
			Map<String, Object> inResult = new HashMap<>(); 
			inResult.putAll(imapper.convertValue(group, Map.class));
			List<Product> productArr = this.sqlSession.selectList("com.wcsoft.mapper.ProductMapper.selectByAnyKey", imapper.convertValue(group, Map.class));
			if(CollectionUtils.isNotEmpty(productArr)) {
				inResult.put("productArr", productArr);
				result.add(inResult);
			}
		}
		return result;
	
	}

	@Override
	public List<Product> queryProduct(Map<String, Object> params) {
		return this.sqlSession.selectList("com.wcsoft.mapper.ProductMapper.selectByAnyKey", params);
	}

	@Override
	@CacheEvict(cacheNames = "productDataList")
	public void addProduct(Map<String, Object> params) {
		params.put("productId", UuidUtils.getIdString("p"));
		params.put("createDatetime", new Date());
		this.sqlSession.insert("com.wcsoft.mapper.ProductMapper.insert", params);
	}

	@Override
	@CacheEvict(cacheNames = "productDataList")
	public void editProduct(Map<String, Object> params) {
		this.sqlSession.update("com.wcsoft.mapper.ProductMapper.updateByPrimaryKey", params);
		
	}

	@Override
	@CacheEvict(cacheNames = "productDataList")
	public void delProduct(String productId) {
		this.sqlSession.delete("com.wcsoft.mapper.ProductMapper.deleteByPrimaryKey", productId);
	}

	@Override
	public List<Map<String, Object>> allProductGroup(Map<String, Object> params) {
		return this.sqlSession.selectList("com.wcsoft.mapper.ProductMapper.selectProductGroup", params);
	}

}

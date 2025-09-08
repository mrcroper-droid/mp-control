package com.wcsoft.service;

import java.util.List;
import java.util.Map;

import com.wcsoft.entity.Product;

/**
 * <p>
 * 产品表 服务类
 * </p>
 *
 * @author Roper
 * @since 2024-10-11
 */
public interface ProductService{

	List<Map<String, Object>> allProductGroup(Map<String, Object> params);
	
	List<Map<String, Object>> queryDataList(Map<String, Object> params);
	
	List<Product> queryProduct(Map<String, Object> params);
	
	void addProduct(Map<String, Object> params);
	
    void editProduct(Map<String, Object> params);
	
    void delProduct(String productId);
}

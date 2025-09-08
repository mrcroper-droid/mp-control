package com.wcsoft.controller;

import java.util.Map;

import org.apache.commons.collections4.MapUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcsoft.entity.RespBean;
import com.wcsoft.utils.Constants;

@RestController
@RequestMapping("/product")
public class ProductController extends com.wcsoft.controller.BaseAbstractController{
	
	@PostMapping("/queryDataList")
	public RespBean queryDataList(@RequestBody Map<String, Object> params) {
		return RespBean.ok(Constants.Q_OK, productService.queryDataList(params));
	}

	@PostMapping("/queryProduct")
	public RespBean queryProduct(@RequestBody Map<String, Object> params) {
		return RespBean.ok(Constants.Q_OK, productService.queryProduct(params));
	}
	
	@PostMapping("/queryProductGroup")
	public RespBean queryProductGroup(@RequestBody Map<String, Object> params) {
		return RespBean.ok(Constants.Q_OK, productService.allProductGroup(params));
	}
	
	@PostMapping("/addProduct")
	public RespBean addProduct(@RequestBody Map<String, Object> params) {
		productService.addProduct(params);
		return RespBean.ok(Constants.P_OK);
	}
	@PostMapping("/editProduct")
	public RespBean editProduct(@RequestBody Map<String, Object> params) {
		productService.editProduct(params);
		return RespBean.ok(Constants.P_OK);
	}
	@PostMapping("/delProduct")
	public RespBean delProduct(@RequestBody Map<String, Object> params) {
		productService.delProduct(MapUtils.getString(params, "productId"));
		return RespBean.ok(Constants.P_OK);
	}
//	@PostMapping("getWxCode")
//	public RespBean getWxCode(@RequestBody Map<String, Object> params) {
//		return RespBean.ok(Constants.Q_OK, wxService.wxQrCode(params));
//	}
}

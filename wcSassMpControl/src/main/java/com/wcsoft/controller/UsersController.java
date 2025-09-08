package com.wcsoft.controller;


import java.util.Map;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wcsoft.entity.RespBean;
import com.wcsoft.utils.Constants;
import com.wcsoft.utils.Utils;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Roper
 * @since 2023-10-11
 */
@RestController
@RequestMapping("/users")
public class UsersController extends BaseAbstractController {

	
	@PostMapping("/createUser")
	public RespBean createUser(@RequestBody Map<String, Object> params) {
		Utils.checkParams(params, "username", "password");
		usersService.createUser(params);
		return RespBean.ok(Constants.P_OK);
	}
	
	@PostMapping("/updateUser")
	public RespBean updateUser(@RequestBody Map<String, Object> params) {
		usersService.updateUsers(params);
		return RespBean.ok(Constants.P_OK);
	}
	
	
}

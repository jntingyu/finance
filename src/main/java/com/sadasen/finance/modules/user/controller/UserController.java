package com.sadasen.finance.modules.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sadasen.core.common.GlobalConsts;
import com.sadasen.core.response.JsonResult;
import com.sadasen.core.response.status.Status;
import com.sadasen.finance.base.BaseController;
import com.sadasen.finance.modules.user.dto.UserDto;
import com.sadasen.finance.modules.user.entity.User;
import com.sadasen.finance.modules.user.service.UserService;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
@RestController
public class UserController extends BaseController {
	
	private static final long serialVersionUID = 5851995341518049077L;
	
	@Autowired
	private UserService userService;
	
	@PostMapping(value="/register")
	public JsonResult register(UserDto dto) {
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setPassword(dto.getPassword());
		user = userService.save(user);
		
		if(null==user) {
			return JsonResult.instance(Status.SYSTEM_ERROR);
		}
		if(-1L==user.getId()) {
			return JsonResult.instance(Status.REQUEST_LACK, "用户名及密码不能为空！");
		}
		if(-2L==user.getId()) {
			return JsonResult.instance(Status.REQUEST_VALID, "用户名已被注册！");
		}
		return JsonResult.instance(user);
	}
	
	@PostMapping("/login")
	public JsonResult login(UserDto userDto) {
		User user = userService.findToLogin(userDto);
		
		if(null==user) {
			return JsonResult.instance(Status.REQUEST_NOT_EXISTS, "用户名或者密码不正确");
		}
		super.getRequest().getSession().setAttribute(GlobalConsts.LOGIN_USER, user);
		return JsonResult.instance(user);
	}
	
	@GetMapping("/logout")
	public void logout(HttpServletResponse response) {
		super.getRequest().getSession().removeAttribute(GlobalConsts.LOGIN_USER);
		try {
			response.sendRedirect("/page/login");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}

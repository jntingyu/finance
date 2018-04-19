package com.sadasen.finance.modules.user.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping("/user")
public class UserController extends BaseController {
	
	private static final long serialVersionUID = 5851995341518049077L;
	
	@Autowired
	private UserService userService;
	
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

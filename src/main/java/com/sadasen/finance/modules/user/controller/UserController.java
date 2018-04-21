package com.sadasen.finance.modules.user.controller;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.sadasen.core.common.GlobalConsts;
import com.sadasen.core.response.JsonResult;
import com.sadasen.core.response.status.Status;
import com.sadasen.finance.base.BaseController;
import com.sadasen.finance.common.Consts;
import com.sadasen.finance.modules.user.entity.User;
import com.sadasen.finance.modules.user.service.UserService;
import com.sadasen.finance.util.JMailUtil;
import com.sadasen.finance.util.Utils;
import com.sadasen.util.DateUtil;
import com.sadasen.util.RegexUtil;
import com.sadasen.util.StringUtil;

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
	
	@PutMapping("/changePwd")
	public JsonResult changePwd(String oldPassword, String newPassword) {
		// 验证用户名密码是否符合规则
		if(null==newPassword || !RegexUtil.match(Consts.Regex.PASSWORD, newPassword)) {
			return JsonResult.instance(Status.REQUEST_LACK, "密码不符合规则");
		}
		
		User user = userService.findById(Utils.getLoginUserId(getRequest()));
		if(!user.getPassword().equals(Utils.MD5(oldPassword))) {
			return JsonResult.instance(Status.REQUEST_VALID, "原密码不正确");
		}
		
		try {
			int r = userService.modifyPwdById(user.getId(), Utils.MD5(newPassword));
			System.out.println(r);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.instance(Status.SYSTEM_ERROR);
		}
		return JsonResult.instance("修改成功");
	}
	
	@GetMapping("/user/bindingEmail")
	public JsonResult bindingEmail(String email) {
		// 验证邮箱格式
		if(!RegexUtil.match(Consts.Regex.EMAIL, email)) {
			return JsonResult.instance(Status.REQUEST_LACK, "请输入正确的邮箱");
		}
		
		User loginUser = Utils.getLoginUser(getRequest());
		String uuidDateTime = Consts.Cache.BINDING_EMAIL_CACHE.get(loginUser.getUserName());
		if(StringUtil.isNotEmpty(uuidDateTime)) {
			try {
				String dateTime = uuidDateTime.split("::")[1];
				long time = DateUtil.stringToDateFormat(dateTime, "yyyyMMddHHmmss").getTime();
				long now = new Date().getTime();
				// 如果上次的请求不到一分钟，则提示用户请求频繁
				if(now-time < 60*1000) {
					return JsonResult.instance(Status.REQUEST_FAILURE, "您的请求过于频繁，请稍后再试");
				}
			} catch (ParseException e) {
				e.printStackTrace();
				System.out.println("缓存数据处理出错");
				return JsonResult.instance(Status.SYSTEM_ERROR);
			}
		}
		
		String uuid = UUID.randomUUID().toString();	
		String dateTime = DateUtil.dateToStringFormat(new Date(), "yyyyMMddHHmmss");
		Consts.Cache.BINDING_EMAIL_CACHE.put(loginUser.getUserName(), uuid+"::"+dateTime+"::"+email);
		
		new Thread() {
			
			@Override
			public void run() {
				try {
					JMailUtil.sendForUpdatePwd(email, loginUser.getUserName(), uuid);
				} catch (MessagingException e) {
					e.printStackTrace();
					System.out.println("发送邮件出错");
				}
			};
			
		}.start();
		
		return JsonResult.instance();
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
	
	@GetMapping("/myInfo")
	public JsonResult myInfo() {
		try {
			return JsonResult.instance(userService.findById(Utils.getLoginUserId(getRequest())));
		} catch (Exception e) {
			return JsonResult.instance(Status.SYSTEM_ERROR);
		}
	}
	
	@PutMapping("/changeProfilePic")
	public JsonResult changeProfilePic(@RequestParam("imageData") MultipartFile imageData) {
		return JsonResult.instance();
	}

}

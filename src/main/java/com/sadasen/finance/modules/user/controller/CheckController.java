package com.sadasen.finance.modules.user.controller;

import java.text.ParseException;
import java.util.Date;
import java.util.UUID;

import javax.mail.MessagingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sadasen.core.common.GlobalConsts;
import com.sadasen.core.response.JsonResult;
import com.sadasen.core.response.status.Status;
import com.sadasen.finance.base.BaseController;
import com.sadasen.finance.common.Consts;
import com.sadasen.finance.modules.user.dto.UserDto;
import com.sadasen.finance.modules.user.entity.User;
import com.sadasen.finance.modules.user.service.UserService;
import com.sadasen.finance.util.JMailUtil;
import com.sadasen.finance.util.Utils;
import com.sadasen.util.DateUtil;
import com.sadasen.util.RegexUtil;
import com.sadasen.util.StringUtil;

/**
 * @date 2018年4月19日
 * @author lei.ys
 * @addr company
 * @desc
 */
@RestController
@RequestMapping("/check")
public class CheckController extends BaseController {

	private static final long serialVersionUID = -5018659694884752053L;
	
	@Autowired
	private UserService userService;
	
	/**
	 * 用户注册
	 * @param dto
	 * @return
	 */
	@PostMapping(value="/register")
	public JsonResult register(UserDto dto) {
		// 验证用户名密码是否符合规则
		if(null==dto.getUserName() || null==dto.getPassword() 
				|| !RegexUtil.match(Consts.Regex.USER_NAME, dto.getUserName()) 
				|| !RegexUtil.match(Consts.Regex.PASSWORD, dto.getPassword())) {
			return JsonResult.instance(Status.REQUEST_LACK, "用户名或密码不符合规则！");
		}
		// 验证用户名是否被注册
		if(null!=userService.findByUserName(dto.getUserName())) {
			return JsonResult.instance(Status.REQUEST_VALID, "用户名已被注册！");
		}
		// 新增用户
		User user = new User();
		user.setUserName(dto.getUserName());
		user.setPassword(Utils.MD5(dto.getPassword()));
		
		try {
			user = userService.save(user);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.instance(Status.SYSTEM_ERROR);
		}
		return JsonResult.instance(user);
	}
	
	/**
	 * 用户登录
	 * @param userDto
	 * @return
	 */
	@PostMapping("/login")
	public JsonResult login(UserDto userDto) {
		userDto.setPassword(Utils.MD5(userDto.getPassword()));
		User user = userService.findToLogin(userDto);
		
		if(null==user) {
			return JsonResult.instance(Status.REQUEST_NOT_EXISTS, "用户名或者密码不正确");
		}
		super.getRequest().getSession().setAttribute(GlobalConsts.LOGIN_USER, user);
		return JsonResult.instance(user);
	}
	
	/**
	 * 用户忘记密码时，请求发送邮件接口
	 * @param userName
	 * @return
	 */
	@GetMapping("/forgetPwd/{userName}")
	public JsonResult forgetPwdGetEmail(@PathVariable("userName") String userName) {
		String uuidDateTime = Consts.Cache.CHANGE_PWD_CACHE.get(userName);
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
		
		User user = userService.findByUserName(userName);
		if(null==user) {
			return JsonResult.instance(Status.REQUEST_NOT_EXISTS, "用户名不存在");
		}
		if(null==user.getEmail()) {
			return JsonResult.instance(Status.REQUEST_LACK, "该用户未绑定邮箱地址");
		}
		
		String uuid = UUID.randomUUID().toString();	
		String dateTime = DateUtil.dateToStringFormat(new Date(), "yyyyMMddHHmmss");
		Consts.Cache.CHANGE_PWD_CACHE.put(userName, uuid+"::"+dateTime);
		
		new Thread() {
			
			@Override
			public void run() {
				try {
					JMailUtil.sendForUpdatePwd(user.getEmail(), userName, uuid);
				} catch (MessagingException e) {
					e.printStackTrace();
					System.out.println("发送邮件出错");
				}
			};
			
		}.start();
		
		return JsonResult.instance();
	}
	
	/**
	 * 用户忘记密码时，修改密码接口
	 * @param userName
	 * @return
	 */
	@PutMapping("/forgetPwd/{userName}")
	public JsonResult forgetPwdUpdatePwd(@PathVariable("userName") String userName, 
			@RequestParam("password") String password, @RequestParam("validId") String validId) {
		String uuidDateTime = Consts.Cache.CHANGE_PWD_CACHE.get(userName);
		if(StringUtil.isEmpty(uuidDateTime)) {
			return JsonResult.instance(Status.REQUEST_VALID, "该链接已失效");
		}
		String uuid = uuidDateTime.split("::")[0];
		if(!uuid.equals(validId)) {
			return JsonResult.instance(Status.REQUEST_VALID, "错误链接");
		}
		// 验证用户名密码是否符合规则
		if(null==password || !RegexUtil.match(Consts.Regex.PASSWORD, password)) {
			return JsonResult.instance(Status.REQUEST_LACK, "密码不符合规则！");
		}
		
		UserDto userDto = new UserDto();
		userDto.setUserName(userName);
		userDto.setPassword(Utils.MD5(password));
		try {
			int r = userService.modifyPwdByUserName(userDto);
			System.out.println(r);
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.instance(Status.SYSTEM_ERROR);
		}
		return JsonResult.instance("修改成功");
	}
	
	@GetMapping("/bindingEmail/{userName}/{validId}")
	public JsonResult bindingEmail(@PathVariable("userName") String userName, @PathVariable("validId") String validId) {
		String uuidDateTime = Consts.Cache.BINDING_EMAIL_CACHE.get(userName);
		if(StringUtil.isEmpty(uuidDateTime)) {
			return JsonResult.instance(Status.REQUEST_FAILURE, "该链接已失效");
		}
		
		String[] bindInfo = uuidDateTime.split("::");
		try {
			// 验证uuid值是否一致
			String uuid = bindInfo[0];
			if(!uuid.equals(validId)) {
				return JsonResult.instance(Status.REQUEST_VALID, "错误链接");
			}
			// 验证时间是否超过一个小时
			long requestTime = DateUtil.stringToDateFormat(bindInfo[1], "yyyyMMddHHmmss").getTime();
			long now = new Date().getTime();
			if(now - requestTime > 60*60*1000) {
				Consts.Cache.BINDING_EMAIL_CACHE.remove(userName);
				return JsonResult.instance(Status.REQUEST_VALID, "该链接已失效");
			}
			// 绑定邮箱
			String email = bindInfo[2];
			int r = userService.modifyEmailByUserName(userName, email);
			System.out.println(r);
			return JsonResult.instance();
		} catch (ParseException e) {
			e.printStackTrace();
			return JsonResult.instance(Status.SYSTEM_ERROR);
		}
	}

}

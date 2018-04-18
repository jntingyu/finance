package com.sadasen.finance.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.sadasen.core.common.GlobalConsts;
import com.sadasen.finance.util.Utils;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Component
public class LoginInterceptor implements HandlerInterceptor {
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Utils.printInfo("request uri -> " + request.getRequestURI());
		if(!request.getRequestURI().endsWith("/login") || !request.getRequestURI().endsWith("/register")) {
			if(null==request.getSession().getAttribute(GlobalConsts.LOGIN_USER)) {
				request.getRequestDispatcher("/page/login").forward(request, response);
				return false;
			}
		}
		return true;
	}

}

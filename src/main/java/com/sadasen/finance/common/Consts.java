package com.sadasen.finance.common;

import java.util.HashMap;
import java.util.Map;

/**
 * @date 2018年4月19日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class Consts {
	
	/**
	 * 缓存MAP的常量类
	 */
	public static class Cache {
		
		public static final Map<String, String> CHANGE_PWD_CACHE = new HashMap<>();
		
	}
	
	/**
	 * 正则表达式常量类
	 */
	public class Regex {
		
		// 用户名的正则表达式（字母或者数字组成的2-20位字符）
		public static final String USER_NAME = "[0-9A-Za-z]{2,20}";
		// 密码的正则表达式（不包含中文的6-15位字符）
		public static final String PASSWORD = "^[^\\u4e00-\\u9fa5]{6,15}";
		
	}

}

package com.sadasen.finance.util;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sadasen.core.common.GlobalConsts;
import com.sadasen.finance.modules.user.entity.User;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class Utils implements Serializable {
	
	private static final long serialVersionUID = 3998903802827987642L;
	
	private static final ObjectMapper objectMapper = new ObjectMapper();
	public static final Map<String, String> deMap = new HashMap<>();
	
	static {
		String t1 = "1234567890abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ*";
		String t2 = "qwertyuiopASDFGHJKLzxcvbnm*ZXCVBNMasdfghjklQWERTYUIOP0987654321";
		for(int i=0;i<t1.length();i++) {
			deMap.put(t2.charAt(i)+"", t1.charAt(i)+"");
		}
	}
	
	private Utils() {
	}

	/**
	 * 获得当前登录用户
	 * @param request
	 * @return
	 */
	public static User getLoginUser(HttpServletRequest request) {
		return (User) request.getSession().getAttribute(GlobalConsts.LOGIN_USER);
	}
	
	/**
	 * 获得当前登录用户的ID
	 * @param request
	 * @return
	 */
	public static long getLoginUserId(HttpServletRequest request) {
		return getLoginUser(request).getId();
	}
	
	public static String dbDecrypt(String s) {
		StringBuilder sb = new StringBuilder();
		for(char c : s.toCharArray()) {
			sb.append(deMap.get(c+""));
		}
		return sb.toString();
	}
	
	/**
	 * Object对象转为json字符串
	 * @param object
	 * @return
	 */
	public static String writeJson(Object object) {
		try {
			return objectMapper.writeValueAsString(object);
		} catch (JsonProcessingException e) {
			Utils.printError("jackson write json string error : "+e);
		}
		return null;
	}
	
	public static void printInfo(String string) {
		System.out.println("LOG INFO : " + string);
	}
	
	public static void printError(String string) {
		System.out.println("LOG ERROR : " + string);
	}
	
	public static void printDebug(String string) {
		System.out.println("LOG DEBUG : " + string);
	}
	
	
	public static void main(String[] args) {
		String s = "abcdefg";
		String end3 = s.substring(s.length()-3);
		System.out.println(end3);
	}

}

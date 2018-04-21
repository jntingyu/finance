package com.sadasen.util;

import com.sadasen.finance.common.Consts;

/**
 * @date 2018年4月20日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class RegexUtilTest {
	
	public static void main(String[] args) {
		System.out.println(RegexUtil.match(Consts.Regex.EMAIL, "leiys1.3_@sina.com"));
	}

}

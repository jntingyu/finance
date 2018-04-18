package com.sadasen.finance.modules.account.dto;

import com.sadasen.finance.base.BaseBean;

/**
 * @date 2018年3月9日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class AccountDto extends BaseBean {
	
	private static final long serialVersionUID = -3245872440563196006L;
	
	private String name;
	
	public AccountDto() {
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

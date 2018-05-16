package com.sadasen.finance.modules.bill.dto;

import com.sadasen.finance.base.BaseBean;

/**
 * @date 2018年5月14日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class BillDto extends BaseBean {

	private static final long serialVersionUID = -3003381325356256405L;
	
	private long userId;
	private String name;
	
	public BillDto() {
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}

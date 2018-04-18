package com.sadasen.finance.modules.statistics.dto;

import com.sadasen.finance.base.BaseBean;

public class StatsPara extends BaseBean {
	
	private static final long serialVersionUID = -5719157792718827271L;
	
	private long userId;
	private int type;
	private String startDate;
	private String endDate;
	
	public StatsPara() {
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

}

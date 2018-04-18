package com.sadasen.finance.modules.record.dto;

import com.sadasen.finance.base.BaseBean;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class RecordPara extends BaseBean {
	
	private static final long serialVersionUID = -4733591081162238329L;
	
	private long userId;
	private int type;
	private String startDate;
	private String endDate;
	
	public RecordPara() {
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

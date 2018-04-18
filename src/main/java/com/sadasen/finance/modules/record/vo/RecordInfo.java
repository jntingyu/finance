package com.sadasen.finance.modules.record.vo;

import com.sadasen.finance.base.BaseBean;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class RecordInfo extends BaseBean {
	
	private static final long serialVersionUID = -6910403905793053588L;
	
	private long id;
	private String recordDate;
	private String name;
	private double amount;
	
	public RecordInfo() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

}

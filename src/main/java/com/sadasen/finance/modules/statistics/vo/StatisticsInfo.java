package com.sadasen.finance.modules.statistics.vo;

import com.sadasen.finance.base.BaseBean;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class StatisticsInfo extends BaseBean {
	
	private static final long serialVersionUID = 8221287760373215030L;
	
	private String fieldOne;
	private String fieldTwo;
	
	public StatisticsInfo() {
	}

	public String getFieldOne() {
		return fieldOne;
	}

	public void setFieldOne(String fieldOne) {
		this.fieldOne = fieldOne;
	}

	public String getFieldTwo() {
		return fieldTwo;
	}

	public void setFieldTwo(String fieldTwo) {
		this.fieldTwo = fieldTwo;
	}

}

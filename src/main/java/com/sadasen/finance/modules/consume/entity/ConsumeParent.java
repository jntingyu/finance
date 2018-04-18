package com.sadasen.finance.modules.consume.entity;

import com.sadasen.finance.base.BaseBean;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class ConsumeParent extends BaseBean {

	private static final long serialVersionUID = -2155230938003608138L;
	
	private long parentId;
	private long subId;
	private int level;
	
	public ConsumeParent() {
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public long getSubId() {
		return subId;
	}

	public void setSubId(long subId) {
		this.subId = subId;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

}

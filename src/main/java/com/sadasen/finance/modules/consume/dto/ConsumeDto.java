package com.sadasen.finance.modules.consume.dto;

import com.sadasen.finance.base.BaseBean;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class ConsumeDto extends BaseBean {

	private static final long serialVersionUID = 1488826095077701662L;
	
	private long parentId;
	private String name;
	private int type;
	
	public ConsumeDto() {
	}

	public long getParentId() {
		return parentId;
	}

	public void setParentId(long parentId) {
		this.parentId = parentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

}

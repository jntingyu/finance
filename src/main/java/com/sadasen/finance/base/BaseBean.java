package com.sadasen.finance.base;

import java.io.Serializable;

import com.sadasen.finance.util.Utils;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
public class BaseBean implements Serializable {
	
	private static final long serialVersionUID = -6505829968284485116L;

	@Override
	public String toString() {
		return Utils.writeJson(this);
	}

}

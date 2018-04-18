package com.sadasen.finance.modules.user.entity;

import org.beetl.sql.core.annotatoin.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sadasen.finance.base.BaseBean;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Table(name="sys_user")
public class User extends BaseBean {
	
	private static final long serialVersionUID = 8699126083368028250L;
	
	private long id;
	private String userName;
	private String nickName;
	@JsonIgnore
	private String password;
	private String regTime;

	public User() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

}

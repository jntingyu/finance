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
	private String email;
	private String profilePic;
	private String regTime;
	private long indexBillId;
	// 用户
	private long currentBillId;

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getProfilePic() {
		return profilePic;
	}

	public void setProfilePic(String profilePic) {
		this.profilePic = profilePic;
	}

	public String getRegTime() {
		return regTime;
	}

	public void setRegTime(String regTime) {
		this.regTime = regTime;
	}

	public long getIndexBillId() {
		return indexBillId;
	}

	public void setIndexBillId(long indexBillId) {
		this.indexBillId = indexBillId;
	}

	public long getCurrentBillId() {
		return currentBillId;
	}

	public void setCurrentBillId(long currentBillId) {
		this.currentBillId = currentBillId;
	}

}

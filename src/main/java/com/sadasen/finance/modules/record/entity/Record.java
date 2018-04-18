package com.sadasen.finance.modules.record.entity;

import java.util.Date;

import org.beetl.sql.core.annotatoin.Table;

import com.sadasen.finance.base.BaseBean;

/**
 * @date 2018年3月23日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Table(name="t_record")
public class Record extends BaseBean {

	private static final long serialVersionUID = 170686457291395829L;
	
	private long id;
	private long userId;
	private long consumeId;
	private long wayId;
	private long accountId;
	private String recordDate;
	private String damount;
	private int amount;
	private int type;
	private String remark;
	private Date createTime;
	
	public Record() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getUserId() {
		return userId;
	}

	public void setUserId(long userId) {
		this.userId = userId;
	}

	public long getConsumeId() {
		return consumeId;
	}

	public void setConsumeId(long consumeId) {
		this.consumeId = consumeId;
	}

	public long getWayId() {
		return wayId;
	}

	public void setWayId(long wayId) {
		this.wayId = wayId;
	}

	public long getAccountId() {
		return accountId;
	}

	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}

	public String getRecordDate() {
		return recordDate;
	}

	public void setRecordDate(String recordDate) {
		this.recordDate = recordDate;
	}

	public String getDamount() {
		return damount;
	}

	public void setDamount(String damount) {
		this.damount = damount;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}

package com.sadasen.finance.modules.consume.service;

import java.util.List;

import com.sadasen.finance.modules.consume.entity.Consume;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
public interface ConsumeService {
	
	public Consume save(Consume consume);
	
	public Consume findById(long id);
	
	public String getMaxCodeByParent(long id);
	
	public List<Consume> findByParent(Consume param);

}

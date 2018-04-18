package com.sadasen.finance.modules.consume.service.impl;

import java.util.Date;
import java.util.List;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sadasen.finance.modules.consume.dao.ConsumeDao;
import com.sadasen.finance.modules.consume.entity.Consume;
import com.sadasen.finance.modules.consume.entity.ConsumeParent;
import com.sadasen.finance.modules.consume.service.ConsumeService;
import com.sadasen.finance.util.Utils;
import com.sadasen.util.StringUtil;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Service
public class ConsumeServiceImpl implements ConsumeService {
	
	@Autowired
	private SQLManager sqlManager;
	@Autowired
	private ConsumeDao consumeDao;

	@Override
	public Consume save(Consume consume) {
		
		// 根据parentId获得上级分类
		Consume parent = consumeDao.single(consume.getParentId());
		
		// parentId不为0，parent为null或userId不为本人，则说明参数parentId参数不正确
		if(0!=consume.getParentId() && (null==parent || consume.getUserId()!=parent.getUserId())) {
			Utils.printError("parent id is not 0, parent consume is null. param wrong.");
			consume.setId(-1L);
			return consume;
		}
		Utils.printInfo("get parent consume : " + parent);
		
		/** code设置 */
		// 根据type和parentId获得最大的code
		String maxCode = consumeDao.findMaxCodeByParentId(consume.getType(), consume.getParentId());
		// 根据获取到的code，获得分类的最后三位code
		int codeInt = this.getCodeEnd3(maxCode);
		// codeInt如果为0，不符合code规则
		if(0!=codeInt) {
			// parentId为0表示一级分类，直接赋值codeInt，否则为上级分类的code+codeInt
			if(0==consume.getParentId()) {
				consume.setCode(""+codeInt);
			} else {
				consume.setCode(parent.getCode()+codeInt);
			}
		} else {
			Utils.printError("add consume set code error. maxCode : " + maxCode);
			return null;
		}
		Utils.printInfo("add consume set code : " + consume.getCode());
		
		// 分类级别
		if(0==consume.getParentId()) {
			consume.setLevel(0);
		} else {
			consume.setLevel(parent.getLevel()+1);
		}
		Utils.printInfo("add consume set level : " + consume.getLevel());
		
		/** sort设置 */
		int sort = consumeDao.countByParentId(consume.getParentId());
		consume.setSort(sort);
		
		consume.setHasChild(0);
		consume.setCreateTime(new Date());
		
		int r = sqlManager.insertTemplate(consume, true);
		
		Consume temp = consume;
		while(0L!=temp.getParentId()) {
			temp = consumeDao.single(temp.getParentId());
			ConsumeParent cp = new ConsumeParent();
			cp.setSubId(consume.getId());
			cp.setParentId(temp.getId());
			cp.setLevel(temp.getLevel());
			consumeDao.insertConsumeParent(cp);
		}
		
		/** 父分类hasChild设置 */
		if(null!=parent && 0==parent.getHasChild()) {
			consumeDao.updateHasChild(parent.getId(), 1);
		}
		
		if(1==r) {
			return consume;
		}
		return null;
	}

	@Override
	public Consume findById(long id) {
		return consumeDao.single(id);
	}
	
	@Override
	public String getMaxCodeByParent(long id) {
		
		return null;
	}
	
	/**
	 * 根据获取到的code，在最后三位数字中加1，如果code为空，则直接返回100
	 * 如果code长度不为3的倍数，说明获得的code不正确，则返回0
	 * @param code
	 * @return
	 */
	private int getCodeEnd3(String code) {
		if(StringUtil.isEmpty(code)) {
			return 100;
		} else if(code.length()%3 != 0) {
			return 0;
		}
		String endCode = code.substring(code.length()-3);
		return Integer.parseInt(endCode)+1;
	}

	@Override
	public List<Consume> findByParent(Consume param) {
		return consumeDao.selectByParent(param);
	}

}

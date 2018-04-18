package com.sadasen.finance.modules.account.service.impl;

import java.util.List;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sadasen.finance.modules.account.dao.AccountDao;
import com.sadasen.finance.modules.account.entity.Account;
import com.sadasen.finance.modules.account.service.AccountService;
import com.sadasen.util.StringUtil;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Service
public class AccountServiceImpl implements AccountService {
	
	@Autowired
	private SQLManager sqlManager;
	@Autowired
	private AccountDao accountDao;

	@Override
	public Account save(Account account) {
		if(StringUtil.isEmpty(account.getName())) {
			account.setId(-1L);
			return account;
		}
		
		int sort = accountDao.countByUser(account.getUserId());
		account.setSort(sort);
		
		int r = sqlManager.insertTemplate(account, true);
		if(1==r) {
			return account;
		}
		return null;
	}
	
	@Override
	public Account findById(long id) {
		return accountDao.single(id);
	}

	@Override
	public List<Account> findListByUser(long userId) {
		return accountDao.selectListByUser(userId);
	}

	@Override
	public List<Account> findListByWay(long userId, long wayId) {
		return accountDao.selectListByWay(userId, wayId);
	}

	@Override
	public List<Account> findListByNotInWay(long userId, long wayId) {
		return accountDao.selectListByNotInWay(userId, wayId);
	}

}

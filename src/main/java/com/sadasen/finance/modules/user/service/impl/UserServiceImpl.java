package com.sadasen.finance.modules.user.service.impl;

import org.beetl.sql.core.SQLManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sadasen.finance.modules.user.dao.UserDao;
import com.sadasen.finance.modules.user.dto.UserDto;
import com.sadasen.finance.modules.user.entity.User;
import com.sadasen.finance.modules.user.service.UserService;
import com.sadasen.util.DateTimeUtil;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	private SQLManager sqlManager;
	@Autowired
	private UserDao userDao;

	@Override
	public User save(User user) {
		user.setNickName(user.getUserName());
		user.setRegTime(DateTimeUtil.getNow());
		int r = sqlManager.insertTemplate(user, true);
		if(1==r) {
			return user;
		}
		return null;
	}

	@Override
	public User findToLogin(UserDto userDto) {
		return userDao.selectToLogin(userDto);
	}
	
	@Override
	public User findById(long id) {
		return sqlManager.single(User.class, id);
	}
	
	@Override
	public User getById(long id) {
		return userDao.single(id);
	}

	@Override
	public User findByUserName(String userName) {
		return userDao.selectByUserName(userName);
	}

	@Override
	public int modifyPwdByUserName(UserDto userDto) {
		return userDao.updatePasswordByUserName(userDto);
	}
	
	@Override
	public int modifyPwdById(long userId, String password) {
		return userDao.updatePassword(userId, password);
	}

}

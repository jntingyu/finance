package com.sadasen.finance.modules.user.service;

import com.sadasen.finance.modules.user.dto.UserDto;
import com.sadasen.finance.modules.user.entity.User;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
public interface UserService {
	
	public User save(User user);
	
	public User findToLogin(UserDto userDto);
	
	public User findById(long id);
	
	public User getById(long id);
	
	public User findByUserName(String userName);
	
	public int modifyPwdByUserName(UserDto userDto);

}

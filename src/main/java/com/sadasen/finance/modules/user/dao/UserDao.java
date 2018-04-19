package com.sadasen.finance.modules.user.dao;

import org.beetl.sql.core.annotatoin.Param;
import org.beetl.sql.core.mapper.BaseMapper;

import com.sadasen.finance.modules.user.dto.UserDto;
import com.sadasen.finance.modules.user.entity.User;

/**
 * @date 2018年3月8日
 * @author lei.ys
 * @addr company
 * @desc
 */
public interface UserDao extends BaseMapper<User> {
	
	/**
	 * 登录查询（用户名和密码）
	 * @param userDto
	 * @return
	 */
	public User selectToLogin(UserDto userDto);
	
	/**
	 * 根据用户名查询用户信息
	 * @param userName
	 * @return
	 */
	public User selectByUserName(@Param("userName") String userName);
	
	/**
	 * 根据用户名修改密码
	 * @param userDto
	 * @return
	 */
	public int updatePasswordByUserName(UserDto userDto);

}

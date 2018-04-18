用户表操作

findToLogin
===
	select id, user_name, nick_name, reg_time from sys_user
	where 
	user_name = #userName# and password = #password#
	
selectByUserName
===
	select id, user_name, nick_name, reg_time from sys_user
	where
	user_name = #userName#
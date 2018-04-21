用户表操作

selectToLogin
===
	select id, user_name, nick_name, email, profile_pic, reg_time 
	from sys_user
	where 
	user_name = #userName# and password = #password#
	
selectByUserName
===
	select id, user_name, nick_name, email, profile_pic, reg_time 
	from sys_user
	where
	user_name = #userName#
	
updatePasswordByUserName
===
	update sys_user set password = #password# where user_name = #userName#
	
updatePassword
===
	update sys_user set password = #password# where id = #userId#
	
updateEmailByUserName
===
	update sys_user set email = #email# where user_name = #userName#
	
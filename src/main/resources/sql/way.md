insertWayAccount
===
	insert into t_way_account(way_id, account_id) value (#wayId#, #accountId#)

countByUser
===
	select count(0) from t_way where user_id = #userId#
	
selectListByUser
===
	select id, name, sort from t_way where user_id = #userId#
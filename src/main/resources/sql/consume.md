分类表操作

insertConsumeParent
===
	insert into t_consume_parent(sub_id, parent_id, level) 
	value (#subId#, #parentId#, #level#)

updateHasChild
===
	update t_consume set has_child = #hasChild# where id = #id#

findMaxCodeByParentId
===
	select MAX(code) from t_consume
	where 
	type = #type# and parent_id = #parentId#
	
selectByParent
===
	select id, parent_id, user_id, name, type, code, level, sort, has_child from t_consume
	where type = #type# and parent_id = #parentId# and user_id = #userId#
	
countByParentId
===
	select count(0) from t_consume
	where
	parent_id = #parentId#
	
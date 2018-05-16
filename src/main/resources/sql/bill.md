selectListByCondition
===
	select id, name, type, status
	from t_bill
	where 
	1=1
	
	@if(0!=userId){
	and user_id = #{userId}
	@}
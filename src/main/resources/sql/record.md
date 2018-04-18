selectInfoListByCondition
===
	select a.id, a.record_date, round(a.amount/100,2) as amount, b.name
	from t_record a join t_consume b on a.consume_id = b.id
	where
	a.type = #type# and a.user_id = #userId# 
	@if(isNotEmpty(startDate)){
		and a.record_date >= #startDate# 
	@}
	@if(isNotEmpty(endDate)){
		and a.record_date < #endDate#
	@}
	order by a.record_date desc, a.create_time desc
	
selectAfter30
===
	select record_date from t_record
	where type = #type# and user_id = #userId#
	@if(isNotEmpty(endDate)){
		and record_date < #endDate#
	@}
	order by record_date desc
	limit 30
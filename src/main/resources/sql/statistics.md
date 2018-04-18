selectTotalToday
===
	select ifnull(sum(amount), 0) from t_record
	where
	record_date = CURRENT_DATE() and type = #type# and user_id = #userId#

selectTotalByTimeArea
===
	select ifnull(sum(amount), 0) from t_record
	where 
	record_date between #startDate# and #endDate# and type = #type# and user_id = #userId#
	
selectTotalAll
===
	select ifnull(sum(amount), 0) from t_record
	where
	type = #type# and user_id = #userId#
	
selectAvgAll
===
	select ifnull(sum(amount),0)/(timestampdiff(day,min(record_date),current_date())+1) 
	from t_record
	where
	type = #type# and user_id = #userId#
	
selectEveryMonth
===
	select date_format(record_date, '%Y-%m') as field_one, round(sum(amount)/100,2) as field_two
	from t_record
	where
	type = #type# and user_id = #userId#
	group by field_one
	order by field_one
	
selectConsumeTotal
===
	select c.name as field_one, round(sum(r.amount)/100,2) as field_two 
	from t_record r join t_consume c on r.consume_id = c.id 
	where 
	r.type = #type# and r.user_id = #userId# group by r.consume_id order by field_two desc
	
selectBaseConsumeTotal
===
	select a.name as field_one, round(b.total,2) as field_two 
	from t_consume a join 
	(
		select left(c.code, 3) rcode,sum(r.amount)/100 total 
		from t_record r join t_consume c on r.consume_id = c.id 
		where r.type = #type# and r.user_id = #userId# 
		group by rcode
	) b on a.code = b.rcode
	
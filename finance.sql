-- 用户表
CREATE TABLE IF NOT EXISTS sys_user (
	id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	user_name varchar(30) NOT NULL COMMENT '用户名',
	nick_name varchar(30) DEFAULT NULL COMMENT '昵称',
	password varchar(50) NOT NULL COMMENT '密码',
	reg_time timestamp default current_timestamp comment '注册时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 分类表
CREATE TABLE IF NOT EXISTS t_consume (
	id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	parent_id bigint(20) DEFAULT 0 COMMENT '上级分类ID',
	user_id bigint(20) DEFAULT 0 COMMENT '用户ID',
	name varchar(30) NOT null COMMENT '名称',
	type tinyint(4) DEFAULT 0 COMMENT '类型 0：支出，1：收入',
	code varchar(255) DEFAULT null COMMENT '码',
	level tinyint(4) DEFAULT 0 COMMENT '分类级别',
	sort int(11) DEFAULT 0 COMMENT '排序',
	has_child tinyint(11) DEFAULT 0 COMMENT '是否有子分类',
	create_time timestamp default current_timestamp comment '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS t_consume_parent (
	sub_id bigint(20) not null,
	parent_id bigint(20) not null,
	level tinyint(4) not null,
	primary key (sub_id, parent_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS t_way (
	id bigint(20) not null primary key auto_increment comment 'ID',
	user_id bigint(20) default 0 comment '用户ID',
	name varchar(30) not null comment '方式名称',
	sort int(11) default 0 comment '排序',
	create_time timestamp default current_timestamp comment '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS t_account (
	id bigint(20) not null primary key auto_increment comment 'ID',
	user_id bigint(20) default 0 comment '用户ID',
	name varchar(30) not null comment '方式名称',
	sort int(11) default 0 comment '排序',
	create_time timestamp default current_timestamp comment '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS t_way_account (
	way_id bigint(20) not null,
	account_id bigint(20) not null,
	primary key (way_id, account_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE IF NOT EXISTS t_record (
	id bigint(20) not null primary key auto_increment comment 'ID',
	user_id bigint(20) default 0 comment '用户ID',
	consume_id bigint(20) default 0 comment '分类ID',
	way_id bigint(20) default 0 comment '方式ID',
	account_id bigint(20) default 0 comment '账户ID',
	record_date date default null comment '记账日期',
	amount int(11) default 0 comment '金额',
	type tinyint(4) default 0 comment '类型',
	remark varchar(255) default null comment '备注',
	create_time timestamp default current_timestamp comment '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户表
DROP TABLE IF EXISTS sys_user;
CREATE TABLE IF NOT EXISTS sys_user (
	id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	user_name varchar(30) NOT NULL COMMENT '用户名',
	nick_name varchar(30) DEFAULT NULL COMMENT '昵称',
	password varchar(50) NOT NULL COMMENT '密码',
	email varchar(50) DEFAULT NULL COMMENT '用户邮箱',
	profile_pic varchar(200) DEFAULT NULL COMMENT '头像',
	reg_time timestamp DEFAULT current_timestamp COMMENT '注册时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 记账表
CREATE TABLE IF NOT EXISTS t_bill (
	id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	user_id bigint(20) DEFAULT 0 COMMENT '所属用户',
	name varchar(30) NOT NULL COMMENT '记账名称',
	type tinyint(4) DEFAULT 0 COMMENT '记账类型 0：个人，1：集体，2：旅行，3：出差',
	start_date date NOT NULL COMMENT '记账开始日期',
	end_date date DEFAULT NULL COMMENT '记账结束日期',
	remark varchar(255) DEFAULT NULL COMMENT '备注',
	create_time timestamp DEFAULT current_timestamp COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 用户账单关联表
CREATE TABLE IF NOT EXISTS t_user_bill (
	user_id bigint(20) DEFAULT 0 COMMENT '用户ID',
	bill_id bigint(20) DEFAULT 0 COMMENT '记账ID',
	power tinyint(4) DEFAULT 1 COMMENT '权限，0：不可见，1：可查看，2：可修改，5：创建者',
	PRIMARY KEY (user_id, bill_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 分类表
CREATE TABLE IF NOT EXISTS t_classify (
	id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	parent_id bigint(20) DEFAULT 0 COMMENT '上级分类ID',
	bill_id bigint(20) DEFAULT 0 COMMENT '记账ID',
	name varchar(30) NOT NULL COMMENT '名称',
	code varchar(255) DEFAULT NULL COMMENT '码',
	level tinyint(4) DEFAULT 0 COMMENT '分类级别',
	sort int(11) DEFAULT 0 COMMENT '排序',
	has_child tinyint(11) DEFAULT 0 COMMENT '是否有子分类',
	remark varchar(255) DEFAULT NULL COMMENT '备注',
	create_time timestamp DEFAULT current_timestamp COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 分类父子表
CREATE TABLE IF NOT EXISTS t_classify_parent (
	sub_id bigint(20) NOT NULL COMMENT '子分类ID',
	parent_id bigint(20) NOT NULL COMMENT '父分类ID',
	level tinyint(4) NOT NULL COMMENT '父分类的级别',
	PRIMARY KEY (sub_id, parent_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 账户分类表（途径表）
CREATE TABLE IF NOT EXISTS t_way (
	id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	bill_id bigint(20) DEFAULT 0 COMMENT '记账ID',
	name varchar(30) NOT NULL COMMENT '方式名称',
	sort int(11) DEFAULT 0 COMMENT '排序',
	remark varchar(255) DEFAULT NULL COMMENT '备注',
	create_time timestamp DEFAULT current_timestamp COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 账户表
CREATE TABLE IF NOT EXISTS t_account (
	id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	bill_id bigint(20) DEFAULT 0 COMMENT '记账ID',
	name varchar(30) NOT NULL COMMENT '方式名称',
	sort int(11) DEFAULT 0 COMMENT '排序',
	remark varchar(255) DEFAULT NULL COMMENT '备注',
	create_time timestamp DEFAULT current_timestamp COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 途径账户关联表
CREATE TABLE IF NOT EXISTS t_way_account (
	way_id bigint(20) NOT NULL COMMENT '途径ID',
	account_id bigint(20) NOT NULL COMMENT '账户ID',
	PRIMARY KEY (way_id, account_id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 记录表
CREATE TABLE IF NOT EXISTS t_record (
	id bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'ID',
	bill_id bigint(20) DEFAULT 0 COMMENT '记账ID',
	consume_id bigint(20) DEFAULT 0 COMMENT '分类ID',
	way_id bigint(20) DEFAULT 0 COMMENT '方式ID',
	account_id bigint(20) DEFAULT 0 COMMENT '账户ID',
	record_date date DEFAULT NULL COMMENT '记账日期',
	amount int(11) DEFAULT 0 COMMENT '金额',
	remark varchar(255) DEFAULT NULL COMMENT '备注',
	create_time timestamp DEFAULT current_timestamp COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

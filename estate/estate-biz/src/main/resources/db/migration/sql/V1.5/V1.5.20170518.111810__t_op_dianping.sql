create table t_op_dianping_user
(
	id bigserial not null
		constraint t_op_dianping_user_pkey
			primary key,
	user_type varchar(10),
	account varchar(40),
	nicky varchar(20),
	avatar varchar(200),
	mobile varchar(16),
	description varchar(60),
	openid varchar(60),
	session_key varchar(60),
	create_time timestamp default now()
)
;

create table t_op_dianping_corp
(
	id bigserial not null
		constraint t_op_dianping_corp_pkey
			primary key,
	name varchar(60) not null,
	status varchar(4) default 'not'::character varying,
	positive_count integer default 0,
	negative_count integer default 0,
	visit_count bigint default 0,
	creater_id bigint,
	is_deleted boolean default false,
	create_time timestamp default now(),
	update_time timestamp,
	comment_count bigint default 0
)
;

create table t_op_dianping_judgement
(
	id bigserial not null
		constraint t_op_dianping_judgement_pkey
			primary key,
	user_id bigint,
	corp_id bigint,
	judgement varchar(20) default 'NOT_YET'::character varying,
	create_time timestamp default now()
)
;

create table t_op_dianping_corp_visit
(
	user_id bigint,
	corp_id bigint
)
;

create table t_op_dianping_comment
(
	id bigserial not null
		constraint t_op_dianping_comment_pkey
			primary key,
	user_id bigint,
	corp_id bigint,
	shopfront varchar(40) not null,
	content varchar(400),
	positive_count bigint default 0,
	create_time timestamp default now(),
	tags varchar(60)
)
;

create table t_op_dianping_comment_like_map
(
	comment_id bigint,
	user_id bigint
)
;

create table t_op_dianping_deprecated_comment_tag_map
(
	id bigserial not null
		constraint t_op_dianping_comment_tag_map_pkey
			primary key,
	comment_id bigint,
	tag_id bigint
)
;

create table t_op_dianping_deprecated_tag
(
	id bigserial not null
		constraint t_op_dianping_tg_pkey
			primary key,
	name varchar(20),
	is_deleted boolean default false,
	create_time timestamp default now(),
	update_time timestamp default now()
)
;
ALTER TABLE t_op_dianping_judgement
    add UNIQUE (user_id, corp_id);

ALTER TABLE t_op_dianping_comment_like_map
    add COLUMN create_time timestamp default now();

ALTER TABLE t_op_dianping_corp_visit
    add COLUMN create_time timestamp default now();
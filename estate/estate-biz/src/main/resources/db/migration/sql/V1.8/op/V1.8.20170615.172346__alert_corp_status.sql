ALTER TABLE t_op_dianping_corp
    ALTER COLUMN status SET DEFAULT 'NEW';

ALTER TABLE t_op_dianping_corp
    ALTER COLUMN status TYPE varchar(10);

update t_op_dianping_corp set status ='NEW';
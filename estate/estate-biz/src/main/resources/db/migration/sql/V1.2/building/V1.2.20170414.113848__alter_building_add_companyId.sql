ALTER TABLE t_building  ADD company_id BIGINT;

UPDATE t_building SET company_id = 1;
ALTER TABLE t_company
  DROP COLUMN license,
  DROP COLUMN locked;

ALTER TABLE t_company
  ADD UNIQUE (secret_key);

ALTER TABLE t_company
  ADD COLUMN city_id BIGINT,
  ADD COLUMN parent_id BIGINT,
  ADD COLUMN abbr VARCHAR(32),
  ADD COLUMN type VARCHAR(20),
  ADD COLUMN boss_id BIGINT,
  ADD COLUMN status VARCHAR(20);
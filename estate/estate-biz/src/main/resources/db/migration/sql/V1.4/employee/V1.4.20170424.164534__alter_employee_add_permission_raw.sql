ALTER TABLE t_employee
  ADD COLUMN follow_rent_id BIGINT;

ALTER TABLE t_employee
  ADD COLUMN sell_contact_count INTEGER,
  ADD COLUMN last_sell_count_time TIMESTAMP;

ALTER TABLE t_employee
  ADD COLUMN rent_contact_count INTEGER,
  ADD COLUMN last_rent_count_time TIMESTAMP;
ALTER TABLE t_employee
  ADD COLUMN device_id VARCHAR(50);
ALTER TABLE t_employee
  ADD COLUMN sys_admin BOOLEAN NOT NULL DEFAULT FALSE;
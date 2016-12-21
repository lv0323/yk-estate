CREATE TABLE t_settings (
  id         SERIAL PRIMARY KEY,
  name_space VARCHAR(32) NOT NULL,
  key        VARCHAR(32) NOT NULL,
  value      VARCHAR(32) NOT NULL,
  priority   INT,
  is_deleted CHAR(1) DEFAULT 'N',
  UNIQUE (name_space, key, value)
);
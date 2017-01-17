CREATE TABLE t_setting (
  id         BIGSERIAL PRIMARY KEY,
  name_space VARCHAR(32) NOT NULL,
  key        VARCHAR(32) NOT NULL,
  value      VARCHAR(1024) NOT NULL,
  priority   INT,
  is_deleted CHAR(1) DEFAULT 'N',
  UNIQUE (name_space, key)
);
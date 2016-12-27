CREATE TABLE t_user (
  id           BIGSERIAL PRIMARY KEY,
  create_by_id BIGINT,
  create_time  TIMESTAMP,
  update_by_id BIGINT,
  update_time  TIMESTAMP,
  version      INT,
  owner_id     BIGINT,
  is_deleted   CHAR(1) DEFAULT 'N',
  user_name    VARCHAR(24),
  real_name    VARCHAR(24),
  card_id      VARCHAR(18),
  salt         VARCHAR(64),
  hash         VARCHAR(64),
  email        VARCHAR(32),
  mobile       VARCHAR(16),
  description  VARCHAR(64)
);

CREATE TABLE t_token (
  id           BIGSERIAL PRIMARY KEY,
  create_by_id BIGINT,
  create_time  TIMESTAMP,
  update_by_id BIGINT,
  update_time  TIMESTAMP,
  version      INT,
  owner_id     BIGINT,
  is_deleted   CHAR(1) DEFAULT 'N',
  hash         VARCHAR(256),
  expire_time  TIMESTAMP
);


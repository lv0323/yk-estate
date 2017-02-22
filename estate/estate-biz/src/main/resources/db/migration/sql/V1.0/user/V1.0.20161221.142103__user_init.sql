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
  avatar_id    BIGINT,
  description  VARCHAR(64),
  client_id    INT
);

ALTER SEQUENCE t_user_id_seq RESTART WITH 10000;

CREATE TABLE T_TOKEN (
  ID            BIGSERIAL PRIMARY KEY,
  USER_ID       VARCHAR(20),
  CLIENT_ID     INT,
  TOKEN         VARCHAR(400),
  EXPIRED_TIME  TIMESTAMP,
  REFRESH_TOKEN VARCHAR(400)
);
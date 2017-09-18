CREATE TABLE t_franchisee_apply (
  id          BIGSERIAL,
  city        VARCHAR(20),
  type        VARCHAR(20),
  name        VARCHAR(20),
  gender      VARCHAR(10),
  mobile      VARCHAR(20),
  qq          VARCHAR(20),
  email       VARCHAR(50),
  message     VARCHAR(1000),
  create_time TIMESTAMP NOT NULL       DEFAULT NOW()
);
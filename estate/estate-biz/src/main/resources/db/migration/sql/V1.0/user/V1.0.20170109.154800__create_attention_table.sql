CREATE TABLE t_favorite (
  id             BIGSERIAL PRIMARY KEY,
  target_id      BIGINT,
  domain_type    VARCHAR(20),
  user_id        BIGINT,
  attention_time TIMESTAMP,
  UNIQUE (target_id, domain_type, user_id)
);
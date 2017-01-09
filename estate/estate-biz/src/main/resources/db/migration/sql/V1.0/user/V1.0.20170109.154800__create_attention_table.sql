CREATE TABLE t_attention (
  id             BIGSERIAL PRIMARY KEY,
  community_id   BIGINT,
  user_id        BIGINT,
  attention_time TIMESTAMP,
  UNIQUE (community_id, user_id)
);
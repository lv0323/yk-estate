CREATE TABLE t_follow (
  id              BIGSERIAL PRIMARY KEY, --id
  follower_id         BIGINT REFERENCES t_user (id),
  domain_type     VARCHAR(80) NOT NULL,
  target_id       BIGINT NOT NULL ,
  create_time    TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_fo_user ON t_follow (domain_type,target_id, follower_id);
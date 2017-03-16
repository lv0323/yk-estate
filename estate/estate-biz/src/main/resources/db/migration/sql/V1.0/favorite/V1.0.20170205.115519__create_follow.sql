CREATE TABLE t_favorite (
  id          BIGSERIAL PRIMARY KEY, --id
  follower_id BIGINT REFERENCES t_user (id),
  domain_type VARCHAR(80) NOT NULL,
  target_id   BIGINT      NOT NULL,
  type        VARCHAR(20) NOT NULL,
  create_time TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP

);
CREATE INDEX ON t_favorite (domain_type, target_id, follower_id);
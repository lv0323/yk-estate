CREATE TABLE t_grant (
  id           BIGSERIAL PRIMARY KEY,
  target_id    BIGINT      NOT NULL,
  target_type  VARCHAR(15) NOT NULL,
  permission   VARCHAR(40) NOT NULL,
  scope        VARCHAR(15),
  limits       INT,
  grant_by_id  BIGINT,
  update_by_id BIGINT,
  is_deleted   BOOLEAN     NOT NULL DEFAULT FALSE,
  create_time  TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
  update_time  TIMESTAMP            DEFAULT CURRENT_TIMESTAMP
);
CREATE TABLE t_file_description (
  id          BIGSERIAL PRIMARY KEY,
  owner_id    BIGINT,
  owner_type  VARCHAR(16),
  custom_type VARCHAR(16),
  file_type   VARCHAR(16),
  target      VARCHAR(16),
  path        VARCHAR(128),
  is_deleted  BOOL NOT NULL DEFAULT FALSE
);

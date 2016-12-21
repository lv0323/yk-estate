CREATE TABLE t_file_descriptor (
  id          BIGSERIAL PRIMARY KEY,
  owner_type  VARCHAR(16),
  owner_id    BIGINT,
  custom_type VARCHAR(16),
  file_type   VARCHAR(16),
  target      VARCHAR(16),
  path        VARCHAR(128),
  is_deleted  BOOL NOT NULL DEFAULT FALSE
);

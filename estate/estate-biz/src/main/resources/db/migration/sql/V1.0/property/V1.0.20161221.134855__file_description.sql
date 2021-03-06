CREATE TABLE t_file_description (
  id           BIGSERIAL PRIMARY KEY,
  owner_id     BIGINT,
  owner_type   VARCHAR(16),
  custom_type  VARCHAR(16),
  file_type    VARCHAR(16),
  file_process INT               DEFAULT 0,
  target       VARCHAR(16),
  path         VARCHAR(128),
  priority     INT,
  is_deleted   BOOL NOT NULL     DEFAULT FALSE,
  create_time  TIMESTAMP         DEFAULT CURRENT_TIMESTAMP,
  updatte_time TIMESTAMP         DEFAULT CURRENT_TIMESTAMP,
  ext          VARCHAR(200)
);

CREATE TABLE t_event (
  id          BIGSERIAL PRIMARY KEY,
  uuid        VARCHAR(50) NOT NULL,
  type        VARCHAR(30) NOT NULL,
  domain_id   BIGINT      NOT NULL,
  domain_type VARCHAR(15) NOT NULL,
  content     VARCHAR(1000),
  create_time TIMESTAMP   NOT NULL      DEFAULT CURRENT_TIMESTAMP, --创建时间
  update_time TIMESTAMP   NOT NULL      DEFAULT CURRENT_TIMESTAMP, --更新时间
  is_deleted  BOOLEAN     NOT NULL      DEFAULT FALSE
);

CREATE INDEX ON t_event (uuid);

CREATE TABLE t_event_process (
  id          BIGSERIAL PRIMARY KEY,
  uuid        VARCHAR(50) NOT NULL,
  type        VARCHAR(30) NOT NULL,
  domain_id   BIGINT      NOT NULL,
  domain_type VARCHAR(15) NOT NULL,
  content     VARCHAR(1000),
  processor   VARCHAR(30) NOT NULL,
  status      VARCHAR(15) NOT NULL,
  create_time TIMESTAMP   NOT NULL      DEFAULT CURRENT_TIMESTAMP, --创建时间
  update_time TIMESTAMP   NOT NULL      DEFAULT CURRENT_TIMESTAMP, --更新时间
  is_deleted  BOOLEAN     NOT NULL      DEFAULT FALSE
);

CREATE INDEX ON t_event_process (uuid, processor);


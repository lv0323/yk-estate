CREATE TABLE t_audit
(
  id            BIGSERIAL PRIMARY KEY,
  company_id    BIGINT      NOT NULL,
  department_id BIGINT      NOT NULL,
  operator_id   BIGINT      NOT NULL,
  subject       VARCHAR(30) NOT NULL,
  target_id     BIGINT      NOT NULL,
  domain_type   VARCHAR(20) NOT NULL,
  content       VARCHAR(1000),
  create_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);


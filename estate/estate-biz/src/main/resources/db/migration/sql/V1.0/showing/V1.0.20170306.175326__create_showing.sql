CREATE TABLE t_showing (
  id            BIGSERIAL PRIMARY KEY,
  customer_id   BIGINT    NOT NULL,
  fang_id       BIGINT    NOT NULL,
  company_id    BIGINT    NOT NULL,
  department_id BIGINT    NOT NULL,
  employee_id   BIGINT    NOT NULL,
  process       VARCHAR(15),
  create_time   TIMESTAMP NOT NULL      DEFAULT CURRENT_TIMESTAMP, --创建时间
  update_time   TIMESTAMP NOT NULL      DEFAULT CURRENT_TIMESTAMP, --更新时间
  is_deleted    BOOLEAN   NOT NULL      DEFAULT FALSE
);


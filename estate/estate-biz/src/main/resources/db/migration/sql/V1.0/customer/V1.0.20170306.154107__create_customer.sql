CREATE TABLE t_customer (
  id               BIGSERIAL PRIMARY KEY,
  name             VARCHAR(15) NOT NULL,
  source           VARCHAR(15) NOT NULL,
  mobile           VARCHAR(20) NOT NULL,
  company_id       BIGINT      NOT NULL,
  department_id    BIGINT      NOT NULL,
  employee_id      BIGINT      NOT NULL,
  a_mobile         VARCHAR(20),
  b_mobile         VARCHAR(20),
  qq               VARCHAR(20),
  we_chat          VARCHAR(20),
  email            VARCHAR(20),
  identity_source  VARCHAR(15),
  identity_no      VARCHAR(30),
  gender           VARCHAR(1),
  resident_address VARCHAR(100),
  status           VARCHAR(15),
  level            VARCHAR(15),
  purpose          VARCHAR(15),
  time_limits      VARCHAR(15),
  create_time      TIMESTAMP   NOT NULL      DEFAULT CURRENT_TIMESTAMP, --创建时间
  update_time      TIMESTAMP   NOT NULL      DEFAULT CURRENT_TIMESTAMP, --更新时间
  is_deleted       BOOLEAN     NOT NULL      DEFAULT FALSE
);


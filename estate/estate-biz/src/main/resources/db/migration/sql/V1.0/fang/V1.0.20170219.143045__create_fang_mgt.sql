CREATE TABLE t_fang_info_owner (
  id            BIGSERIAL PRIMARY KEY,
  fang_id       BIGINT      NOT NULL,
  company_id    BIGINT      NOT NULL,
  department_id BIGINT      NOT NULL,
  employee_id   BIGINT      NOT NULL,
  reason        VARCHAR(20) NOT NULL,
  is_deleted    BOOLEAN     NOT NULL  DEFAULT FALSE,
  create_time   TIMESTAMP             DEFAULT CURRENT_TIMESTAMP,
  update_time   TIMESTAMP             DEFAULT CURRENT_TIMESTAMP
);


CREATE TABLE t_fang_follow (
  id            BIGSERIAL PRIMARY KEY,
  fang_id       BIGINT       NOT NULL,
  company_id    BIGINT       NOT NULL,
  department_id BIGINT       NOT NULL,
  employee_id   BIGINT       NOT NULL,
  follow_type   VARCHAR(20)  NOT NULL,
  content       VARCHAR(500) NOT NULL,
  is_deleted    BOOLEAN      NOT NULL  DEFAULT FALSE,
  create_time   TIMESTAMP              DEFAULT CURRENT_TIMESTAMP,
  update_time   TIMESTAMP              DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE t_fang_check (
  id            BIGSERIAL PRIMARY KEY,
  fang_id       BIGINT  NOT NULL,
  company_id    BIGINT  NOT NULL,
  department_id BIGINT  NOT NULL,
  employee_id   BIGINT  NOT NULL,
  advantage     VARCHAR(500),
  disadvantage  VARCHAR(500),
  is_deleted    BOOLEAN NOT NULL  DEFAULT FALSE,
  create_time   TIMESTAMP         DEFAULT CURRENT_TIMESTAMP,
  update_time   TIMESTAMP         DEFAULT CURRENT_TIMESTAMP
);
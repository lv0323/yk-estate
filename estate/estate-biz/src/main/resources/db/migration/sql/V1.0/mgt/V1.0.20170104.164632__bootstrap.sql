DROP TABLE t_company;
CREATE TABLE t_company
(
  id           BIGSERIAL PRIMARY KEY,
  name         VARCHAR(64),
  short_name   VARCHAR(16),
  license      VARCHAR(32),
  address      VARCHAR(128),
  introduction VARCHAR,
  secret_key   VARCHAR(32)
);

DROP TABLE t_department;
CREATE TABLE t_department
(
  id         BIGSERIAL PRIMARY KEY,
  parent_id  BIGINT,
  company_id BIGINT,
  name       VARCHAR(64),
  short_name VARCHAR(16),
  telephone  VARCHAR(16),
  address    VARCHAR(128)
);

DROP TABLE t_employee;
CREATE TABLE t_employee
(
  id            BIGSERIAL PRIMARY KEY,
  company_id    BIGINT,
  department_id BIGINT,
  job_id        BIGINT,
  is_boss       BOOLEAN DEFAULT FALSE,
  is_agent      BOOLEAN DEFAULT FALSE,
  mobile        CHAR(11),
  password      CHAR(32),
  salt          CHAR(32),
  name          VARCHAR(8),
  gender        CHAR(1),
  idcard_number CHAR(18),
  wechat        VARCHAR(32),
  status        VARCHAR(8),
  entry_date    DATE    DEFAULT CURRENT_DATE
);
CREATE UNIQUE INDEX t_employee_mobile_uindex
  ON t_employee (mobile);
CREATE INDEX t_employee_password_index
  ON t_employee (password);

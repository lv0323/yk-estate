CREATE TABLE t_company
(
  id           BIGSERIAL PRIMARY KEY,
  name         VARCHAR(64),
  license      VARCHAR(32),
  address      VARCHAR(128),
  introduction VARCHAR,
  secret_key   VARCHAR(32),
  start_date   DATE,
  end_date     DATE,
  locked       BOOLEAN   DEFAULT FALSE,
  is_deleted   BOOLEAN   DEFAULT FALSE,
  create_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE t_department
(
  id              BIGSERIAL PRIMARY KEY,
  parent_id       BIGINT      NOT NULL,
  company_id      BIGINT      NOT NULL,
  name            VARCHAR(64) NOT NULL,
  telephone       VARCHAR(16),
  address         VARCHAR(128),
  city_id         BIGINT,
  district_id     BIGINT,
  sub_district_id BIGINT,
  longitude       NUMERIC(12, 9),
  latitude        NUMERIC(12, 9),
  is_deleted      BOOLEAN   DEFAULT FALSE,
  create_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE t_position
(
  id          BIGSERIAL PRIMARY KEY,
  company_id  BIGINT      NOT NULL,
  name        VARCHAR(16) NOT NULL,
  type        VARCHAR(16) NOT NULL,
  note        VARCHAR(128),
  is_deleted  BOOLEAN   DEFAULT FALSE,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE t_employee
(
  id            BIGSERIAL PRIMARY KEY,
  company_id    BIGINT NOT NULL,
  department_id BIGINT NOT NULL,
  position_id   BIGINT NOT NULL,
  is_boss       BOOLEAN   DEFAULT FALSE,
  is_agent      BOOLEAN   DEFAULT FALSE,
  avatar_id     BIGINT,
  mobile        CHAR(11),
  password      CHAR(32),
  salt          CHAR(32),
  name          VARCHAR(8),
  gender        CHAR(1),
  idcard_number CHAR(18),
  wechat        VARCHAR(32),
  status        VARCHAR(8),
  quit          BOOLEAN   DEFAULT FALSE,
  entry_date    DATE      DEFAULT CURRENT_DATE,
  create_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time   TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UNIQUE (company_id, mobile)
);


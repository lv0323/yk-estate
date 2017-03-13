CREATE TABLE t_contract (
  id                 BIGSERIAL PRIMARY KEY,
  fang_id            BIGINT         NOT NULL,
  company_id         BIGINT         NOT NULL,
  department_id      BIGINT         NOT NULL,
  employee_id        BIGINT         NOT NULL,
  house_type         VARCHAR(15)    NOT NULL,
  certif_address     VARCHAR(100),
  certif_no          VARCHAR(50),
  estate_area        NUMERIC(10, 5) NOT NULL,
  price              NUMERIC(28, 5) NOT NULL,
  price_unit         VARCHAR(15)    NOT NULL,
  assignor_name      VARCHAR(20)    NOT NULL,
  assignor_mobile    VARCHAR(20)    NOT NULL,
  assignor_id_source VARCHAR(15)    NOT NULL,
  assignor_id_no     VARCHAR(30)    NOT NULL,
  assignee_name      VARCHAR(20)    NOT NULL,
  assignee_mobile    VARCHAR(20)    NOT NULL,
  assignee_id_source VARCHAR(15)    NOT NULL,
  assignee_id_no     VARCHAR(30)    NOT NULL,
  note               VARCHAR(1000),
  biz_type           VARCHAR(15)    NOT NULL,
  type               VARCHAR(15)    NOT NULL,
  process            VARCHAR(15),
  create_time        TIMESTAMP      NOT NULL      DEFAULT CURRENT_TIMESTAMP, --创建时间
  close_time         TIMESTAMP, --关闭时间
  update_time        TIMESTAMP      NOT NULL      DEFAULT CURRENT_TIMESTAMP, --更新时间
  is_deleted         BOOLEAN        NOT NULL      DEFAULT FALSE
);


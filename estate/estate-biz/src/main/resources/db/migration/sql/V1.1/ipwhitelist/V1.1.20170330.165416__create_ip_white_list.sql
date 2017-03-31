CREATE TABLE t_ip_white_list
(
  id          BIGSERIAL PRIMARY KEY,
  company_id  BIGINT      NOT NULL,
  ip          VARCHAR(30) NOT NULL,
  note        VARCHAR(50),
  is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
  create_time TIMESTAMP            DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP            DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE t_company
  ADD COLUMN ip_check BOOLEAN DEFAULT FALSE;

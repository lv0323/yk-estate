CREATE TABLE t_company_signing (
  id          BIGSERIAL PRIMARY KEY,
  company_id  BIGINT,
  part_a_id   BIGINT,
  years       INT,
  store_count INT,
  start_date  DATE,
  end_date    DATE,
  price       NUMERIC(20, 2),
  is_deleted  BOOLEAN   DEFAULT FALSE,
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

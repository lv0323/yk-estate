CREATE TABLE t_fang (
  id               BIGSERIAL PRIMARY KEY,
  house_licence_id BIGINT, -- house_licence_id
  ranking          NUMERIC(10, 2), --评分
  biz              VARCHAR(20), --租售
  avg_price        INT --均价
);

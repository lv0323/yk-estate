CREATE TABLE t_xiao_qu (
  id           BIGSERIAL PRIMARY KEY,
  community_id BIGINT, -- community_id
  ranking      NUMERIC(10, 2), --评分
  avg_price    INT --均价
);

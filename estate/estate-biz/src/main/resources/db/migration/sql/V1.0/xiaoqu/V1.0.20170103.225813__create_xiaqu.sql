CREATE TABLE t_xiao_qu (
  id           BIGSERIAL PRIMARY KEY,
  community_id BIGINT, -- community_id
  ranking      NUMERIC(10, 2), --评分
  avg_price    INTEGER DEFAULT 0,--挂牌均价
  sell_house_count INTEGER DEFAULT 0, --小区挂牌房源数量
  rent_house_count INTEGER DEFAULT 0 --小区出租房源数量
);

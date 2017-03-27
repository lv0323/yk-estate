INSERT INTO t_xiao_qu (community_id, ranking, avg_price, sell_house_count, rent_house_count)
  SELECT
    id,
    0.0,
    0,
    0,
    0
  FROM t_community;
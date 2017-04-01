INSERT INTO public.t_news (title, category, news_url) VALUES ('房产证的办理流程和注意事项', 'INFO', '');
INSERT INTO public.t_news (title, category, news_url) VALUES ('商品房预售和现售要符合什么条件？', 'INFO', '');
INSERT INTO public.t_news (title, category, news_url) VALUES ('奥克兰The Vincent项目发展规划和楼盘户型介绍', 'TOURIST', '');
INSERT INTO public.t_news (title, category, news_url) VALUES ('2017年北京房地产政策新规一览', 'POLICY', '');


INSERT INTO t_ip_white_list (company_id, ip, note) VALUES
  (1, '106.38.112.50', '总部'),
  (1, '123.115.205.89', '门店'),
  (1, '123.119.134.169', '门店');


INSERT INTO t_setting (name_space, key, value) VALUES
  ('FILE', 'fang_force_size', 'true'),
  ('FILE', 'fang_width', '800'),
  ('FILE', 'fang_height', '600');
ALTER TABLE  public.t_fang_o1fy ADD city_id BIGINT  NOT NULL DEFAULT 1;

ALTER TABLE  public.t_fang_pool_district ADD is_default BOOLEAN NOT NULL DEFAULT false;

insert into t_fang_pool_district(city_id, abbr, name, name_kw, is_default) VALUES (1,'others','其他', 'others',true);
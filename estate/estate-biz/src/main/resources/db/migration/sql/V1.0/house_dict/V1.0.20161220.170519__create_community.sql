CREATE TABLE t_city (-- 城市
  id          BIGSERIAL PRIMARY KEY, --id
  abbr        VARCHAR(30) NOT NULL UNIQUE, -- 拼音简称
  name        VARCHAR(20) NOT NULL, -- 中文名
  longitude   NUMERIC(12, 9), -- 经度
  latitude    NUMERIC(12, 9), -- 纬度
  view_region VARCHAR(64), --地图显示区域
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  name_kw     VARCHAR(200)
);

CREATE TABLE t_district (--地区
  id               BIGSERIAL PRIMARY KEY, --id
  city_id          BIGINT      NOT NULL, -- 城市id
  abbr             VARCHAR(30) NOT NULL UNIQUE, -- 拼音简称
  name             VARCHAR(20) NOT NULL, -- 中文名
  longitude        NUMERIC(12, 9), -- 经度
  latitude         NUMERIC(12, 9), -- 纬度
  view_region      TEXT, --地图显示区域
  sell_avg_price   DECIMAL(28, 8) DEFAULT 0.00, -- 小区挂牌均价
  sell_house_count INTEGER        DEFAULT 0, --小区挂牌房源数量
  rent_house_count INTEGER        DEFAULT 0, --小区出租房源数量
  create_time      TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
  update_time      TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
  name_kw     VARCHAR(200)
);

CREATE TABLE t_sub_district (--地区
  id               BIGSERIAL PRIMARY KEY, --id
  city_id          BIGINT      NOT NULL, -- 城市id
  abbr             VARCHAR(30) NOT NULL UNIQUE, -- 拼音简称
  name             VARCHAR(20) NOT NULL, -- 中文名
  longitude        NUMERIC(12, 9), -- 经度
  latitude         NUMERIC(12, 9), -- 纬度
  view_region      TEXT, --地图显示区域
  sell_avg_price   DECIMAL(28, 8) DEFAULT 0.00, -- 小区挂牌均价
  sell_house_count INTEGER        DEFAULT 0, --小区挂牌房源数量
  rent_house_count INTEGER        DEFAULT 0, --小区出租房源数量
  create_time      TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
  update_time      TIMESTAMP      DEFAULT CURRENT_TIMESTAMP,
  name_kw     VARCHAR(200)
);

CREATE TABLE t_district_rel (
  id              BIGSERIAL PRIMARY KEY, --id
  district_id     BIGINT NOT NULL, --地区id
  sub_district_id BIGINT NOT NULL, -- 板块id
  create_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time     TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UNIQUE (district_id, sub_district_id)
);


CREATE TABLE t_line (--地铁线路
  id          BIGSERIAL PRIMARY KEY, --id
  city_id     BIGINT      NOT NULL, -- 城市id
  abbr        VARCHAR(30) NOT NULL UNIQUE, --简称
  name        VARCHAR(20) NOT NULL, --中文名
  longitude   NUMERIC(12, 9), -- 经度
  latitude    NUMERIC(12, 9), -- 纬度
  view_region VARCHAR(64), --地图显示区域
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  name_kw     VARCHAR(200)
);

CREATE TABLE t_station (--地铁站点
  id          BIGSERIAL PRIMARY KEY, --id
  abbr        VARCHAR(50) NOT NULL UNIQUE, --站点简称
  city_id     BIGINT      NOT NULL, -- 城市id
  name        VARCHAR(20) NOT NULL, --中文名
  longitude   NUMERIC(12, 9), -- 经度
  latitude    NUMERIC(12, 9), -- 纬度
  view_region VARCHAR(64), --地图显示区域
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  name_kw     VARCHAR(200)
);

CREATE TABLE t_line_station_rel (
  id          BIGSERIAL PRIMARY KEY, --id
  line_id     BIGINT NOT NULL, --线路id
  station_id  BIGINT NOT NULL, -- 站点id
  create_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  UNIQUE (line_id, station_id)
);

CREATE TABLE t_community (
  id                     BIGSERIAL PRIMARY KEY, --id
  name                   VARCHAR(50) NOT NULL, -- 小区名
  alias                  VARCHAR(50), -- 别名
  city_id                BIGINT      NOT NULL, --城市id
  sub_district_id        BIGINT      NOT NULL, --板块id
  near_line              CHAR(1)   DEFAULT 'N', --靠近地铁
  longitude              NUMERIC(12, 9), -- 经度
  latitude               NUMERIC(12, 9), -- 纬度
  address                VARCHAR(100), --地址
  developers             VARCHAR(100), -- 开发商
  structure_type         INT, --建筑结构
  builded_year           INT, -- 竣工年份
  develop_year           VARCHAR(50), --建筑年代
  property_company       VARCHAR(150), --物业公司
  property_company_phone VARCHAR(50), -- 物业公司电话
  property_fee           VARCHAR(100), -- 物业费描述
  parking_space          VARCHAR(100), -- 车位描述
  parking_fee            VARCHAR(100), -- 停车费用描述
  parking_rate           VARCHAR(16), -- 车位配比
  buildings              INT, -- 楼栋数
  houses                 INT, -- 房屋数
  container_rate         VARCHAR(16), --　容积率
  green_rate             VARCHAR(16), -- 绿化率
  lj_id                  VARCHAR(50), -- 链家Id
  create_by_id           BIGINT, --创建者id
  create_time            TIMESTAMP DEFAULT CURRENT_TIMESTAMP, --创建时间
  update_by_id           BIGINT, --更新者id
  update_time            TIMESTAMP, --更新时间
  is_deleted             CHAR(1)   DEFAULT 'N',
  version                INT, --版本号
  name_kw                VARCHAR(200),
  alias_kw               VARCHAR(200)
);

CREATE TABLE t_community_station_rel (
  id           BIGSERIAL PRIMARY KEY, --id
  community_id BIGINT NOT NULL, --小区id
  station_id   BIGINT NOT NULL, -- 站点信息
  distance     INT, --距离
  create_time  TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time  TIMESTAMP,
  UNIQUE (community_id, station_id)
);

CREATE TABLE t_building (
  id           BIGSERIAL PRIMARY KEY,
  community_id BIGINT, --小区id
  name         VARCHAR(30), --楼栋名
  floors       INT, --楼层
  stairs       INT, --梯
  houses       INT, --户
  description  VARCHAR(128),
  create_by_id BIGINT,
  create_time  TIMESTAMP,
  update_by_id BIGINT,
  update_time  TIMESTAMP,
  is_deleted   CHAR(1) DEFAULT 'N',
  VERSION      INT
);

CREATE TABLE t_building_unit (
  id          BIGSERIAL PRIMARY KEY,
  building_id BIGINT,
  unit_name   VARCHAR(30) --单元名
);

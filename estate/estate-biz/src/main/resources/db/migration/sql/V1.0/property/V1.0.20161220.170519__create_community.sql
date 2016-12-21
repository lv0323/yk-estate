CREATE TABLE t_city (-- 城市
  id          BIGSERIAL PRIMARY KEY, --id
  city_abbr   VARCHAR(30) NOT NULL UNIQUE, -- 拼音简称
  city_name   VARCHAR(20) NOT NULL, -- 中文名
  longitude   NUMERIC(12, 9), -- 经度
  latitude    NUMERIC(12, 9), -- 纬度
  view_region VARCHAR(64) --地图显示区域
);

CREATE TABLE t_district (--地区
  id            BIGSERIAL PRIMARY KEY, --id
  city_id       BIGINT      NOT NULL, -- 城市id
  district_abbr VARCHAR(30) NOT NULL UNIQUE, -- 拼音简称
  district_name VARCHAR(20) NOT NULL, -- 中文名
  longitude     NUMERIC(12, 9), -- 经度
  latitude      NUMERIC(12, 9), -- 纬度
  view_region   VARCHAR(64) --地图显示区域
);

CREATE TABLE t_plate (--地区
  id          BIGSERIAL PRIMARY KEY, --id
  plate_abbr  VARCHAR(30) NOT NULL UNIQUE, -- 拼音简称
  plate_name  VARCHAR(20) NOT NULL, -- 中文名
  longitude   NUMERIC(12, 9), -- 经度
  latitude    NUMERIC(12, 9), -- 纬度
  view_region VARCHAR(64) --地图显示区域
);

CREATE TABLE t_district_plate_rel (
  id          BIGSERIAL PRIMARY KEY, --id
  district_id BIGINT NOT NULL, --地区id
  plate_id    BIGINT NOT NULL, -- 板块id
  UNIQUE (district_id, plate_id)
);


CREATE TABLE t_line (--地铁线路
  id        BIGSERIAL PRIMARY KEY, --id
  city_id   BIGINT      NOT NULL, -- 城市id
  line_abbr VARCHAR(30) NOT NULL UNIQUE, --简称
  line_name VARCHAR(20) NOT NULL--中文名
);

CREATE TABLE t_station (--地铁站点
  id           BIGSERIAL PRIMARY KEY, --id
  station_abbr VARCHAR(30) NOT NULL UNIQUE, --站点简称
  station_name VARCHAR(20) NOT NULL --中文名
);

CREATE TABLE t_line_station_rel (
  id         BIGSERIAL PRIMARY KEY, --id
  line_id    BIGINT NOT NULL, --线路id
  station_id BIGINT NOT NULL, -- 站点id
  UNIQUE (line_id, station_id)
);

CREATE TABLE t_community (
  id                     BIGSERIAL PRIMARY KEY, --id
  name                   VARCHAR(64) NOT NULL, -- 小区名
  name_alies             VARCHAR(64), -- 别名
  city_id                BIGINT      NOT NULL, --城市id
  plate_id               BIGINT      NOT NULL, --板块id
  near_line              CHAR(1) DEFAULT 'N', --靠近地铁
  longitude              NUMERIC(12, 9), -- 经度
  latitude               NUMERIC(12, 9), -- 纬度
  address                VARCHAR(60), --地址
  developers             VARCHAR(50), -- 开发商
  structure_type         VARCHAR(16), --建筑结构
  builded_year           INT, -- 竣工日期
  property_company       VARCHAR(50), --物业公司
  property_company_phone VARCHAR(16), -- 物业公司电话
  property_fee_desc      VARCHAR(32), -- 物业费描述
  parking_space_desc     VARCHAR(64), -- 车位描述
  parking_fee_desc       VARCHAR(32), -- 停车费用描述
  buildings              INT, -- 楼栋数
  houses                 INT, -- 房屋数
  house_park_rate        VARCHAR(20), --车位配比
  planing_area           VARCHAR(16), -- 规划面积
  building_area          VARCHAR(16), -- 建筑面积
  container_rate         VARCHAR(16), --　容积率
  green_rate             VARCHAR(16), -- 绿化率
  create_by_id           BIGINT, --创建者id
  create_time            TIMESTAMP, --创建时间
  update_by_id           BIGINT, --更新者id
  update_time            TIMESTAMP, --更新时间
  is_deleted             CHAR(1) DEFAULT 'N',
  version                INT --版本号
);

CREATE TABLE t_community_station_rel (
  id           BIGSERIAL PRIMARY KEY, --id
  community_id BIGINT NOT NULL, --小区id
  station_id   BIGINT NOT NULL, -- 站点信息
  distance     INT, --距离
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

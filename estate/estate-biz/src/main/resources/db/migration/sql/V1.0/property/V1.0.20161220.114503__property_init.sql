CREATE SEQUENCE t_property_dictionary_seq;
CREATE TABLE t_property_dictionary (
  id                     BIGINT NOT NULL DEFAULT nextval('t_property_dictionary_seq'),
  create_by_id           BIGINT,
  create_time            TIMESTAMP,
  update_by_id           BIGINT,
  update_time            TIMESTAMP,
  version                INT,
  owner_id               BIGINT,
  is_deleted             CHAR(1)         DEFAULT 'N',
  name                   VARCHAR(64),
  name_alies             VARCHAR(64),
  position_plate_id      INT, -- 地址所属板块
  position_description   VARCHAR(64),
  latitude               NUMERIC(13, 10), --经度
  longitude              NUMERIC(13, 10), --纬度
  property_type          VARCHAR(16), -- 物业类型
  property_fee_desc      VARCHAR(32), -- 物业费描述
  property_company       VARCHAR(32), --物业公司
  property_company_phone VARCHAR(16), -- 物业公司电话
  parking_space_desc     VARCHAR(64), -- 车位描述
  parking_fee_desc       VARCHAR(32), -- 停车费用描述
  developers             VARCHAR(32), -- 开发商
  buildings              INT, -- 楼栋数
  houses                 INT, -- 房屋数
  builded_year           INT, -- 建造时间
  description            VARCHAR(128), -- 楼盘描述
  planing_area           VARCHAR(16), -- 规划面积
  building_area          VARCHAR(16), -- 建筑面积
  container_rate         VARCHAR(16), --　容积率
  green_rate             VARCHAR(16) -- 绿化率
);

CREATE SEQUENCE t_property_house_dictionary_seq;
CREATE TABLE t_property_house_dictionary (
  id           BIGINT NOT NULL DEFAULT nextval('t_property_house_dictionary_seq'),
  create_by_id BIGINT,
  create_time  TIMESTAMP,
  update_by_id BIGINT,
  update_time  TIMESTAMP,
  version      INT,
  owner_id     BIGINT, -- t_property id
  is_deleted   CHAR(1)         DEFAULT 'N',
  room         INT, -- 室
  ball         INT, -- 厅
  bathroom     INT, -- 卫
  balcony      INT, -- 阳台
  kitchen      INT, -- 厨
  towards      VARCHAR(8), -- 朝向
  area         VARCHAR(8), -- 建筑面积
  area_usage   VARCHAR(8), -- 使用面积
  description  VARCHAR(128)
);

CREATE SEQUENCE t_property_building_dictionary_seq;
CREATE TABLE t_property_building_dictionary (
  id           BIGINT NOT NULL DEFAULT nextval('t_property_building_dictionary_seq'),
  create_by_id BIGINT,
  create_time  TIMESTAMP,
  update_by_id BIGINT,
  update_time  TIMESTAMP,
  version      INT,
  owner_id     BIGINT, -- t_property id
  is_deleted   CHAR(1)         DEFAULT 'N',
  name         VARCHAR(24),
  layers       INT,
  unit_name    VARCHAR(24),
  stairs       INT,
  houses       INT,
  description  VARCHAR(128)
);

CREATE SEQUENCE t_images_seq;
CREATE TABLE t_images (
  id           BIGINT NOT NULL  DEFAULT nextval('t_images_seq'),
  create_by_id BIGINT,
  create_time  TIMESTAMP,
  update_by_id BIGINT,
  update_time  TIMESTAMP,
  version      INT,
  owner_id     BIGINT,
  is_deleted   CHAR(1)          DEFAULT 'N',
  type         VARCHAR(16),
  url          VARCHAR(128),
  description  VARCHAR(128)
);

CREATE TABLE t_settings (
  name_space VARCHAR(32) NOT NULL,
  key        VARCHAR(32) NOT NULL,
  value      VARCHAR(32) NOT NULL,
  priority   INT,
  is_deleted CHAR(1) DEFAULT 'N',
  UNIQUE (name_space, key, value)
);
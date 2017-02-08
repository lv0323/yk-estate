CREATE TABLE t_fang (
  id             BIGSERIAL PRIMARY KEY,
  biz_type       VARCHAR(10)         NOT NULL, --租售
  house_type     VARCHAR(10)         NOT NULL, --用途
  house_sub_type VARCHAR(15)         NOT NULL, --用途类型
  licence_id     BIGINT UNIQUE       NOT NULL, -- licence_id
  xiao_qu_id     BIGINT              NOT NULL, -- xiao_qu_id
  ranking        NUMERIC(10, 2), --评分
  s_counts       INT                 NOT NULL, --室
  t_counts       INT                 NOT NULL, --厅
  c_counts       INT                 NOT NULL, --厨
  w_counts       INT                 NOT NULL, --卫
  yt_counts      INT                 NOT NULL, --阳台
  orientation    VARCHAR(5), --朝向
  decorate       VARCHAR(10), --装修
  estate_area    NUMERIC(10, 5)      NOT NULL, --产证面积
  real_area      NUMERIC(10, 5)      NOT NULL, --室内面积
  publish_price  NUMERIC(28, 5)      NOT NULL, --挂牌价
  price_unit     VARCHAR(10)         NOT NULL, --挂牌价单位
  unit_price     NUMERIC(28, 5)      NOT NULL, --单价
  transfer_price NUMERIC(28, 5), --转让费
  bottom_price   NUMERIC(28, 5), --底价
  resident       VARCHAR(1), --是否可落户
  process        VARCHAR(20)         NOT NULL, --流程状态
  floor_counts   INT                 NOT NULL, --总层高
  floor_type     VARCHAR(10)         NOT NULL, --层高类型
  structure_type VARCHAR(10), --结构
  build_year     INT, --建造时间
  heating_type   VARCHAR(15), --供暖类型
  has_elevator   VARCHAR(1), --是否有电梯
  create_time    TIMESTAMP, --创建时间
  update_time    TIMESTAMP, --更新时间
  publish_time   TIMESTAMP  --挂牌时间
);

CREATE TABLE t_fang_ext (
  id                 BIGSERIAL PRIMARY KEY,
  fang_id            BIGINT UNIQUE  NOT NULL, --fang_id
  level              VARCHAR(10)    NOT NULL, --评级
  showing            VARCHAR(20)    NOT NULL, --看房方式
  delegate_type      VARCHAR(20)    NOT NULL, --委托方式
  delegate_start     TIMESTAMP, --委托开始时间
  delegate_end       TIMESTAMP, --委托结束时间
  status             VARCHAR(20)    NOT NULL, --现状
  source             VARCHAR(15)    NOT NULL, --来源
  certif_type        VARCHAR(10)    NOT NULL, --产证类型
  certif_adress      VARCHAR(100), --房产地址
  certif_no          VARCHAR(50), --房产证号
  property_type      VARCHAR(10)    NOT NULL, --交易权属
  taxes_willing      VARCHAR(15), --税费
  commission_willing VARCHAR(15), --佣金
  purchase_price     NUMERIC(28, 5), --购入价格
  purchaseDate       DATE, --购入时间
  is_only            VARCHAR(1), --唯一住房
  over_years         INTEGER, --满2，满5
  mortgage           VARCHAR(1), --有无贷款抵押
  note               VARCHAR(500) --备注信息
);


CREATE TABLE t_fang_tag (
  id        BIGSERIAL PRIMARY KEY,
  fang_id   BIGINT,
  house_tag VARCHAR(20) NOT NULL --标签
);

CREATE TABLE t_fang_descr (
  id         BIGSERIAL PRIMARY KEY,
  fang_id    BIGINT UNIQUE NOT NULL,
  title      VARCHAR(100), --标题
  core       VARCHAR(500), --核心卖点
  hu_xing    VARCHAR(500), --户型介绍
  zhuang_xiu VARCHAR(500), --装修情况
  quan_shu   VARCHAR(500), --交易权属
  xue_qu     VARCHAR(500), --学区说明
  pei_tao    VARCHAR(500), --周边配套
  shui_fei   VARCHAR(500), --税费解析
  jiao_tong  VARCHAR(500), --交通出行
  xiao_qu    VARCHAR(500), --小区简介
  tou_zi     VARCHAR(500) --投资分析
);


CREATE TABLE t_fang_contact (
  id           BIGSERIAL PRIMARY KEY,
  fang_id      BIGINT NOT NULL,
  ower_name    VARCHAR(20), --业主姓名
  contact_type VARCHAR(10), --联系类别
  contact_info VARCHAR(50) --联系方式
);

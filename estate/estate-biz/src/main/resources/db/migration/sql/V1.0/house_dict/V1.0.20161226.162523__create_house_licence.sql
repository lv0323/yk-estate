CREATE TABLE t_house_licence (
  id               BIGSERIAL PRIMARY KEY, --id
  community_id     BIGINT      NOT NULL, --社区id
  building_id      BIGINT      NOT NULL, --楼栋id
  building_unit_id BIGINT      NOT NULL, --单元id
  house_no         VARCHAR(20) NOT NULL, --房号
  biz_type         VARCHAR(10) NOT NULL, --type
  status           VARCHAR(10) NOT NULL, --status
  create_time      TIMESTAMP   NOT NULL                  DEFAULT CURRENT_TIMESTAMP,
  update_time      TIMESTAMP   NOT NULL                  DEFAULT CURRENT_TIMESTAMP,
  is_deleted       BOOLEAN     NOT NULL                  DEFAULT FALSE
);

ALTER SEQUENCE t_house_licence_id_seq RESTART WITH 100000;
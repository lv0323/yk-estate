CREATE TABLE t_house_licence (
  id               BIGSERIAL PRIMARY KEY, --id
  community_id     BIGINT      NOT NULL, --社区id
  building_id      BIGINT      NOT NULL, --楼栋id
  building_unit_id BIGINT      NOT NULL, --单元id
  house_number     VARCHAR(20) NOT NULL, --房号
  type             VARCHAR(10), --type
  status           VARCHAR(10), --status
  create_time      TIMESTAMP,
  update_time      TIMESTAMP,
  is_deleted       VARCHAR(1)
);

ALTER SEQUENCE t_house_licence_id_seq RESTART WITH 100000;
CREATE TABLE t_information (
  id             BIGSERIAL PRIMARY KEY, --id
  info_title      TEXT, --
  info_summary    TEXT, -- 中文名
  info_content    TEXT, -- 经度
  content_type    VARCHAR(40), -- 纬度
  business_type   VARCHAR(40), --地图显示区域
  sender         BIGINT REFERENCES t_user (id), --地图显示区域
  receiver       BIGINT REFERENCES t_user (id),
  create_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_info_receiver ON t_information (receiver,business_type);

CREATE TABLE t_information_counter (-- 城市
  id             BIGSERIAL PRIMARY KEY, --id
  owner_id       BIGINT REFERENCES t_user (id), -- 拼音简称
  c_info_index        INTEGER DEFAULT 0, -- 拼音简称
  c_monthly_report_index   INTEGER  DEFAULT 0, -- 拼音简称
  notice_index        INTEGER  DEFAULT 0, -- 拼音简称
  create_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_info_counter_owner ON t_information_counter (owner_id);
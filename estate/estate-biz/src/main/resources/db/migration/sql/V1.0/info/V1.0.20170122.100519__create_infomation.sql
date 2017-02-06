CREATE TABLE t_message (
  id             BIGSERIAL PRIMARY KEY, --id
  title      TEXT, --
  summary    TEXT, -- 中文名
  content    TEXT, -- 经度
  content_type    VARCHAR(40), -- 纬度
  business_type   VARCHAR(40), --地图显示区域
  sender_id         BIGINT REFERENCES t_user (id),
  receiver_id       BIGINT REFERENCES t_user (id),
  status         VARCHAR(40) DEFAULT 'UNREAD',
  create_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  uuid    VARCHAR(128) NOT NULL
);
CREATE INDEX idx_ms_receiver ON t_message (receiver_id,business_type);
CREATE INDEX idx_ms_uuid ON t_message (uuid);

CREATE TABLE t_message_counter (-- 城市
  id             BIGSERIAL PRIMARY KEY, --id
  owner_id       BIGINT REFERENCES t_user (id), -- 拼音简称
  c_ms_index        INTEGER DEFAULT 0, -- 拼音简称
  c_m_report_index   INTEGER  DEFAULT 0, -- 拼音简称
  notice_index        INTEGER  DEFAULT 0, -- 拼音简称
  create_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_ms_counter_owner ON t_message_counter (owner_id);
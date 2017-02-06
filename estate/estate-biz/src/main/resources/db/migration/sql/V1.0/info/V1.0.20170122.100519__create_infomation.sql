CREATE TABLE t_message (
  id             BIGSERIAL PRIMARY KEY, --id
  title      TEXT, --
  summary    TEXT, -- 中文名
  content    TEXT, -- 经度
  content_type    VARCHAR(40), -- 纬度
  sender_id         BIGINT REFERENCES t_user (id),
  receiver_id       BIGINT REFERENCES t_user (id),
  status         VARCHAR(40) DEFAULT 'UNREAD',
  create_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  update_time    TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  uuid    VARCHAR(128) NOT NULL
);
CREATE INDEX idx_ms_receiver ON t_message (receiver_id,sender_id, status);
CREATE INDEX idx_ms_uuid ON t_message (uuid);

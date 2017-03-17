CREATE TABLE t_message (
  id           BIGSERIAL PRIMARY KEY, --id
  title        TEXT        NOT NULL, -- 标题
  content      TEXT, -- 内容
  content_type VARCHAR(30),
  domain_id    BIGINT      NOT NULL, -- 内容
  domain_type  VARCHAR(80) NOT NULL, -- 内容类型
  sender_id    BIGINT REFERENCES t_user (id),
  receiver_id  BIGINT REFERENCES t_user (id),
  status       VARCHAR(40) NOT NULL DEFAULT 'UNREAD',
  create_time  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time  TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);
CREATE INDEX idx_ms_receiver
  ON t_message (receiver_id, sender_id, status);

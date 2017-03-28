CREATE TABLE t_news (
  id          BIGSERIAL PRIMARY KEY, --id
  title       VARCHAR(50) NOT NULL, -- 标题
  category    VARCHAR(20),
  image_id    BIGINT,
  news_url    VARCHAR(100),
  is_deleted  BOOLEAN     NOT NULL DEFAULT FALSE,
  create_time TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP,
  update_time TIMESTAMP   NOT NULL DEFAULT CURRENT_TIMESTAMP
);

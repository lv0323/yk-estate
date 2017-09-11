ALTER TABLE t_news
  ADD scope VARCHAR(20);

ALTER TABLE t_news
  ADD summary VARCHAR(200);

UPDATE t_news
SET scope = 'APP';
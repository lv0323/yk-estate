CREATE TABLE t_approval (
  id            BIGSERIAL PRIMARY KEY,
  applyId       BIGINT      NOT NULL,
  approverId    BIGINT,
  type          VARCHAR(20) NOT NULL,
  status        VARCHAR(20) NOT NULL,
  data          JSONB,
  create_time   TIMESTAMP   NOT NULL       DEFAULT CURRENT_TIMESTAMP,
  approval_time TIMESTAMP,
  update_time   TIMESTAMP,
  is_deleted    BOOLEAN     NOT NULL       DEFAULT FALSE
);

CREATE TABLE T_COMMON_APPLICATION (
  ID            BIGSERIAL PRIMARY KEY,
  TYPE          VARCHAR(16) NOT NULL, -- PUBLISH_HOUSE, UN_PUBLISH_HOUSE, PAUSE_HOUSE, SUCCESS_HOUSE,
  STATUS        VARCHAR(16) NOT NULL DEFAULT 'NEW', -- NEW, APPROVED, REJECTED, CLOSED_BY_REVIEWER, CLOSED_BY_APPLICANT

  APPLICANT_ID   BIGINT      NOT NULL,
  APPLY_REASON   VARCHAR(256),

  REVIEWER_ID    BIGINT,
  REVIEWER_COMMENTS VARCHAR(256),

  DOMAIN_ID      BIGINT      NOT NULL, -- external id for this application, could be fang_id, or annual_leave__id, ...
  DOMAIN_FROM    VARCHAR(64), -- external info details, could be house_from_process ...
  DOMAIN_TO      VARCHAR(64), -- external info details, could be house_to_status ...

  CREATE_TIME   TIMESTAMP   NOT NULL       DEFAULT NOW(),
  UPDATE_TIME   TIMESTAMP   NOT NULL       DEFAULT NOW()
);
CREATE INDEX IDX_APPLICANT_ID
  ON T_COMMON_APPLICATION (APPLICANT_ID);
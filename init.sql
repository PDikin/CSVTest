-- Creating tables --
CREATE TABLE IF NOT EXISTS EMPLOYEE (
  ID BIGINT PRIMARY KEY UNIQUE NOT NULL,
  SSOID VARCHAR(255),
  TS VARCHAR(255),
  GRP VARCHAR(255),
  TYPE VARCHAR(255),
  SUBTYPE VARCHAR(255),
  URL VARCHAR(255),
  ORGID VARCHAR(255),
  FOMRID VARCHAR(255),
  CODE VARCHAR(255),
  LTPA VARCHAR(255),
  UDIRRESPONSEA VARCHAR(255),
  YMDH VARCHAR(255));

CREATE SEQUENCE IF NOT EXISTS seq_employee_id INCREMENT BY 1
  START WITH 1 NO CYCLE;

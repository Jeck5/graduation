DROP TABLE IF EXISTS bankaccounts;
DROP SEQUENCE IF EXISTS global_db_seq;
CREATE SEQUENCE global_db_seq
  START WITH 10000;
CREATE TABLE bankaccounts
(
  key       INTEGER PRIMARY KEY DEFAULT nextval('global_db_seq'),
  id        INTEGER UNIQUE NOT NULL,
  balance   DECIMAL NOT NULL
);

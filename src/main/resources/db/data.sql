DELETE FROM bankaccounts;
ALTER SEQUENCE global_db_seq RESTART WITH 100000;

INSERT INTO bankaccounts (key, id, balance) VALUES
  ('1000', '20000', '33334.34'),
  ('1001', '20001', '63334.34'),
  ('1002', '20002', '53334.34'),
  ('1003', '20003', '4334.34'),
  ('1004', '20004', '43334.34');

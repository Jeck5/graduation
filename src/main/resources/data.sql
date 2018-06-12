DELETE FROM USER_ROLES;
DELETE FROM VOTES;
DELETE FROM DISHES;
DELETE FROM RESTAURANTS;
DELETE FROM USERS;

INSERT INTO users (id, name, email, password) VALUES
  ('1000', 'john', 'john@mail.com', 'john'),
  ('1001', 'Mark', 'mark@mail.com', 'mark'),
  ('1002', 'Peter', 'peter@mail.com', 'peter'),
  ('1003', 'Greg', 'greg@mail.com', 'greg');

INSERT INTO user_roles (user_id, role) VALUES
  ('1000', 'ROLE_ADMIN'),
  ('1001', 'ROLE_USER'),
  ('1002', 'ROLE_USER'),
  ('1003', 'ROLE_USER');

INSERT INTO restaurants (id, name) VALUES
  ('1004', 'McDonald`s'),
  ('1005', 'KFC'),
  ('1006', 'Subway');

INSERT INTO dishes (id, name, price, date, restaurant_id) VALUES
  ('1007', 'Hamburger', '99', '2018-05-09', 1004),
  ('1008', 'Big Mac', '199', '2018-05-09', 1004),
  ('1009', 'Cheeseburger', '180', '2018-05-09', 1004),
  ('1010', 'McDouble', '99', '2018-05-09', 1004),
  ('1011', 'Hot Wings', '130', '2018-05-09', 1005),
  ('1012', 'Chicken Tenders', '110', '2018-05-09', 1005),
  ('1013', 'Dark Meat', '140', '2018-05-09', 1005),
  ('1014', 'White Meat', '150', '2018-05-09', 1005),
  ('1015', 'Egg', '30', '2018-05-09', 1006),
  ('1016', 'Cheese', '20', '2018-05-09', 1006),
  ('1017', 'Bacon', '50', '2018-05-09', 1006),
  ('1018', 'Steak', '80', '2018-05-09', 1006),
  ('1019', 'Forrest Ham', '100', '2018-05-09', 1006);

INSERT INTO votes (id, user_id, restaurant_id, date, time) VALUES
  ('1020', '1001', '1004', '2018-05-09', '08:30:00'),
  ('1021', '1001', '1005', '2018-05-09', '10:10:00'),
  ('1022', '1002', '1006', '2018-05-09', '09:00:00'),
  ('1023', '1002', '1004', '2018-05-09', '10:00:00'),
  ('1024', '1003', '1006', '2018-05-09', '08:40:00'),
  ('1025', '1003', '1004', '2018-05-09', '10:20:00'),
  ('1026', '1001', '1005', '2018-05-09', '08:30:00'),
  ('1027', '1001', '1006', '2018-05-09', '09:00:00'),
  ('1028', '1002', '1004', '2018-05-09', '09:00:00'),
  ('1029', '1002', '1005', '2018-05-09', '10:30:00');


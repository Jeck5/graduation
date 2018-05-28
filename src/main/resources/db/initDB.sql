DROP TABLE IF EXISTS votes;
DROP TABLE IF EXISTS dishes;
DROP TABLE IF EXISTS restaurants;
DROP TABLE IF EXISTS user_roles;
DROP TABLE IF EXISTS users;
DROP SEQUENCE IF EXISTS global_db_seq;

CREATE SEQUENCE global_db_seq
  START WITH 10000;

CREATE TABLE users
(
  id       INTEGER PRIMARY KEY DEFAULT nextval('global_db_seq'),
  name     VARCHAR(255) NOT NULL,
  email    VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  enabled  BOOLEAN             DEFAULT TRUE
);
CREATE UNIQUE INDEX users_unique_email_idx
  ON USERS (email);

CREATE TABLE user_roles
(
  user_id INTEGER NOT NULL,
  role    VARCHAR(255),
  CONSTRAINT user_roles_idx UNIQUE (user_id, role),
  FOREIGN KEY (user_id) REFERENCES USERS (id)
  ON DELETE CASCADE
);

CREATE TABLE restaurants
(
  id   INTEGER PRIMARY KEY DEFAULT nextval('global_db_seq'),
  name VARCHAR(255) NOT NULL,
  CONSTRAINT restaurant_name UNIQUE (name)
);

CREATE TABLE votes
(
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_db_seq'),
  user_id       INTEGER NOT NULL,
  restaurant_id INTEGER NOT NULL,
  date          DATE    NOT NULL,
  time          TIME    NOT NULL,
  FOREIGN KEY (user_id) REFERENCES users (id)
  ON DELETE CASCADE,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id)
  ON DELETE CASCADE
);

CREATE TABLE dishes (
  id            INTEGER PRIMARY KEY DEFAULT nextval('global_db_seq'),
  name          VARCHAR(255) NOT NULL,
  price         INTEGER,
  date          DATE         NOT NULL,
  restaurant_id INTEGER      NOT NULL,
  FOREIGN KEY (restaurant_id) REFERENCES restaurants (id)
  ON DELETE CASCADE
);

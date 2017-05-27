CREATE TABLE person (
  id           INT8 NOT NULL PRIMARY KEY AUTO_INCREMENT,
  last_name    TEXT NOT NULL,
  phone_number TEXT NOT NULL
);

INSERT INTO person
(last_name, phone_number)
VALUES
  ('Smith', '0102030405'),
  ('Doe', '0203040506');
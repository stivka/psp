CREATE TABLE users (
  id BIGINT DEFAULT nextval('regular_seq') PRIMARY KEY,
  full_name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL
);
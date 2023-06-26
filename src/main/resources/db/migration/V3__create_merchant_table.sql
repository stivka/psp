CREATE TABLE merchant (
  id BIGINT DEFAULT nextval('regular_seq') PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL
);
CREATE TABLE merchant (
  id BIGINT DEFAULT nextval('merchant_seq') PRIMARY KEY,
  name VARCHAR(255) NOT NULL,
  password VARCHAR(255) NOT NULL,
  email VARCHAR(255) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  last_modified_by VARCHAR(255) NOT NULL,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
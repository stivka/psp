CREATE SEQUENCE customer_seq START 1;

CREATE TABLE customer (
  id BIGINT DEFAULT nextval('customer_seq') PRIMARY KEY,
  FOREIGN KEY (id) REFERENCES app_user (id)
);

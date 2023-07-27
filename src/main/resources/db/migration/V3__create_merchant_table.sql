CREATE SEQUENCE merchant_seq START 1;

CREATE TABLE merchant (
  id BIGINT DEFAULT nextval('merchant_seq') PRIMARY KEY,
  FOREIGN KEY (id) REFERENCES app_user (id)
);
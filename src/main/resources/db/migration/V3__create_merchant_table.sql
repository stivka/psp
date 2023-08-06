CREATE SEQUENCE merchant_seq START 2000;

CREATE TABLE merchant (
  id BIGINT DEFAULT nextval('merchant_seq') PRIMARY KEY,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES app_user (id)
);
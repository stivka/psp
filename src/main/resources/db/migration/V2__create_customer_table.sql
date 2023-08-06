-- since there are one-to-one relationships between user and customer or merchant
-- the app_user id is the same as customer or merchant id
-- to avoid future duplications, when a user is created via the app
-- the sequence initial value is sufficiently large and won't be reached
CREATE SEQUENCE customer_seq START 1000;

CREATE TABLE customer (
  id BIGINT DEFAULT nextval('customer_seq') PRIMARY KEY,
  user_id BIGINT NOT NULL,
  FOREIGN KEY (user_id) REFERENCES app_user (id)
);

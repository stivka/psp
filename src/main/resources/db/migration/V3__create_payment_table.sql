CREATE TABLE payment (
  id BIGINT DEFAULT nextval('regular_seq') PRIMARY KEY,
  amount DECIMAL(10, 2) NOT NULL,
  user_id BIGINT NOT NULL,
  payment_method VARCHAR(50) NOT NULL,
  CONSTRAINT fk_payment_user FOREIGN KEY (user_id) REFERENCES users (id)
);

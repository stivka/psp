CREATE TABLE payment (
  id BIGINT DEFAULT nextval('regular_seq') PRIMARY KEY,
  amount DECIMAL(10, 2) NOT NULL,
  customer_id BIGINT NOT NULL,
  merchant_id BIGINT NOT NULL,
  payment_method VARCHAR(50) NOT NULL,
  CONSTRAINT fk_payment_customer FOREIGN KEY (customer_id) REFERENCES customer (id),
  CONSTRAINT fk_payment_merchant FOREIGN KEY (merchant_id) REFERENCES merchant (id)
);

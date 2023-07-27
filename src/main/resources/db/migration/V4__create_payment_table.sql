CREATE SEQUENCE payment_seq START 1;

CREATE TABLE payment (
  id BIGINT DEFAULT nextval('payment_seq') PRIMARY KEY,
  amount DECIMAL(10, 2) NOT NULL,
  customer_id BIGINT NOT NULL,
  merchant_id BIGINT NOT NULL,
  payment_method VARCHAR(50) NOT NULL,
  created_by VARCHAR(255) NOT NULL,
  creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  last_modified_by VARCHAR(255) NOT NULL,
  last_modified_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
  CONSTRAINT fk_payment_customer FOREIGN KEY (customer_id) REFERENCES customer (id),
  CONSTRAINT fk_payment_merchant FOREIGN KEY (merchant_id) REFERENCES merchant (id)
);

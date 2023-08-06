-- Creating 5 API keys
INSERT INTO api_key (id, key_value, created_by, creation_date)
VALUES 
(1, 'apikey1', CURRENT_USER, CURRENT_TIMESTAMP),
(2, 'apikey2', CURRENT_USER, CURRENT_TIMESTAMP),
(3, 'apikey3', CURRENT_USER, CURRENT_TIMESTAMP),
(4, 'apikey4', CURRENT_USER, CURRENT_TIMESTAMP),
(5, 'apikey5', CURRENT_USER, CURRENT_TIMESTAMP);

-- Creating 3 customers with the created API keys
INSERT INTO app_user (id, full_name, email, user_password, api_key_id, created_by, creation_date)
VALUES 
(1, 'Customer 1', 'customer1@example.com', 'password1', 1, CURRENT_USER, CURRENT_TIMESTAMP),
(2, 'Customer 2', 'customer2@example.com', 'password2', 2, CURRENT_USER, CURRENT_TIMESTAMP),
(3, 'Customer 3', 'customer3@example.com', 'password3', 3, CURRENT_USER, CURRENT_TIMESTAMP);

INSERT INTO customer (id, user_id)
VALUES 
(1, 1),
(2, 2),
(3, 3);

-- Creating 2 merchants with the created API keys
INSERT INTO app_user (id, full_name, email, user_password, api_key_id, created_by, creation_date)
VALUES 
(4, 'Merchant 1', 'merchant1@example.com', 'password1', 4, CURRENT_USER, CURRENT_TIMESTAMP),
(5, 'Merchant 2', 'merchant2@example.com', 'password2', 5, CURRENT_USER, CURRENT_TIMESTAMP);

INSERT INTO merchant (id, user_id)
VALUES 
(1, 4),
(2, 5);

INSERT INTO payment (amount, customer_id, merchant_id, payment_method, created_by, last_modified_by)
VALUES 
(50.25, 1, 1, 'VISA', CURRENT_USER, CURRENT_USER),
(35.00, 1, 2, 'MASTERCARD', CURRENT_USER, CURRENT_USER),
(75.00, 1, 2, 'PAYPAL', CURRENT_USER, CURRENT_USER),
(10.00, 2, 1, 'BANK_TRANSFER', CURRENT_USER, CURRENT_USER),
(25.00, 2, 2, 'BANK_TRANSFER', CURRENT_USER, CURRENT_USER),
(50.00, 2, 1, 'PAYPAL', CURRENT_USER, CURRENT_USER),
(35.75, 3, 2, 'VISA', CURRENT_USER, CURRENT_USER),
(10.00, 3, 1, 'PAYPAL', CURRENT_USER, CURRENT_USER),
(25.00, 3, 2, 'BANK_TRANSFER', CURRENT_USER, CURRENT_USER),
(55.00, 3, 1, 'MASTERCARD', CURRENT_USER, CURRENT_USER);

INSERT INTO customer (name, password, email)
VALUES ('John Doe', 'password1', 'john.doe@example.com'),
       ('Jane Smith', 'password2', 'jane.smith@example.com'),
       ('Charlie Brown', 'password3', 'charlie.brown@example.com');

INSERT INTO merchant (name, password, email)
VALUES ('Merchant One', 'pass1', 'merchant.one@example.com'),
       ('Merchant Two', 'pass2', 'merchant.two@example.com'),
       ('Merchant Three', 'pass3', 'merchant.three@example.com');

INSERT INTO payment (amount, customer_id, merchant_id, payment_method)
VALUES (100.00, (SELECT id FROM customer WHERE email='john.doe@example.com'), (SELECT id FROM merchant WHERE email='merchant.one@example.com'), 'Credit Card'),
       (50.50, (SELECT id FROM customer WHERE email='jane.smith@example.com'), (SELECT id FROM merchant WHERE email='merchant.two@example.com'), 'Debit Card'),
       (200.00, (SELECT id FROM customer WHERE email='charlie.brown@example.com'), (SELECT id FROM merchant WHERE email='merchant.three@example.com'), 'Paypal');

-- Add users
INSERT INTO users (id, full_name, email, password)
VALUES
    (1, 'John Doe', 'john@example.com', 'password123'),
    (2, 'Jane Smith', 'jane@example.com', 'pass456'),
    (3, 'Mike Johnson', 'mike@example.com', 'securePass');

-- Add payments
INSERT INTO payment (id, amount, user_id, payment_method)
VALUES
    (1, 100.50, 1, 'Credit Card'),
    (2, 75.20, 2, 'PayPal'),
    (3, 50.80, 1, 'Bank Transfer'),
    (4, 120.00, 3, 'Credit Card'),
    (5, 90.50, 2, 'Bank Transfer');
    
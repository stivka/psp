INSERT INTO customer (name, password, email, created_by, creation_date, last_modified_by, last_modified_date)
VALUES 
    (
        'John Doe', 
        'password1', 
        'john.doe@example.com',
        'psp_service', 
        CURRENT_TIMESTAMP, 
        'psp_service', 
        CURRENT_TIMESTAMP
    ),
    (
        'Jane Smith', 
        'password2', 
        'jane.smith@example.com',
        'psp_service', 
        CURRENT_TIMESTAMP, 
        'psp_service', 
        CURRENT_TIMESTAMP
    ),
    (
        'Charlie Brown', 
        'password3', 
        'charlie.brown@example.com',
        'psp_service', 
        CURRENT_TIMESTAMP, 
        'psp_service', 
        CURRENT_TIMESTAMP
    );

INSERT INTO merchant (name, password, email, created_by, creation_date, last_modified_by, last_modified_date)
VALUES 
    (
        'Merchant One', 
        'pass1', 
        'merchant.one@example.com',
        'psp_service', 
        CURRENT_TIMESTAMP, 
        'psp_service', 
        CURRENT_TIMESTAMP
    ),
    (
        'Merchant Two', 
        'pass2', 
        'merchant.two@example.com',
        'psp_service', 
        CURRENT_TIMESTAMP, 
        'psp_service', 
        CURRENT_TIMESTAMP
    ),
    (
        'Merchant Three', 
        'pass3', 
        'merchant.three@example.com',
        'psp_service', 
        CURRENT_TIMESTAMP, 
        'psp_service', 
        CURRENT_TIMESTAMP
    );

INSERT INTO payment (amount, customer_id, merchant_id, payment_method, created_by, creation_date, last_modified_by, last_modified_date)
VALUES 
    (
        100.00,
        (
            SELECT id
            FROM customer
            WHERE email = 'john.doe@example.com'
        ),
        (
            SELECT id
            FROM merchant
            WHERE email = 'merchant.one@example.com'
        ),
        'BANK_TRANSFER',
        'psp_service', 
        CURRENT_TIMESTAMP, 
        'psp_service', 
        CURRENT_TIMESTAMP
    ),
    (
        50.50,
        (
            SELECT id
            FROM customer
            WHERE email = 'jane.smith@example.com'
        ),
        (
            SELECT id
            FROM merchant
            WHERE email = 'merchant.two@example.com'
        ),
        'VISA',
        'psp_service', 
        CURRENT_TIMESTAMP, 
        'psp_service', 
        CURRENT_TIMESTAMP
    ),
    (
        200.00,
        (
            SELECT id
            FROM customer
            WHERE email = 'charlie.brown@example.com'
        ),
        (
            SELECT id
            FROM merchant
            WHERE email = 'merchant.three@example.com'
        ),
        'PAYPAL',
        'psp_service', 
        CURRENT_TIMESTAMP, 
        'psp_service', 
        CURRENT_TIMESTAMP
    );

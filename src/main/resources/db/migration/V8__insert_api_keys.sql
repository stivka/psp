-- The V7__insert_api_keys.sql file
INSERT INTO api_key (key_value, merchant_id, key_role, created_by, creation_date)
VALUES (
        'key1',
        (
            SELECT id
            FROM merchant
            WHERE email = 'merchant.one@example.com'
        ),
        'ROLE_MERCHANT',
        'psp_service',
        CURRENT_TIMESTAMP
    ),
    (
        'key2',
        (
            SELECT id
            FROM merchant
            WHERE email = 'merchant.two@example.com'
        ),
        'ROLE_MERCHANT',
        'psp_service',
        CURRENT_TIMESTAMP
    ),
    (
        'key3',
        (
            SELECT id
            FROM merchant
            WHERE email = 'merchant.three@example.com'
        ),
        'ROLE_MERCHANT',
        'psp_service',
        CURRENT_TIMESTAMP
    ),
    (
        'key4',
        null,
        'ROLE_ADMIN',
        'psp_service',
        CURRENT_TIMESTAMP
    );
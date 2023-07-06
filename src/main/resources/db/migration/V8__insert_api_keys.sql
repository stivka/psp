-- The V7__insert_api_keys.sql file
INSERT INTO api_key (key_value, merchant_id, key_role)
VALUES (
        'key1',
        (
            SELECT id
            FROM merchant
            WHERE email = 'merchant.one@example.com'
        ),
        'MERCHANT'
    ),
    (
        'key2',
        (
            SELECT id
            FROM merchant
            WHERE email = 'merchant.two@example.com'
        ),
        'MERCHANT'
    ),
    (
        'key3',
        (
            SELECT id
            FROM merchant
            WHERE email = 'merchant.three@example.com'
        ),
        'MERCHANT'
    ),
    ('key4', null, 'ADMIN');
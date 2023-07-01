-- The V7__insert_api_keys.sql file
INSERT INTO api_key (key, merchant_id)
VALUES (
        'key1',
        (
            SELECT id
            FROM merchant
            WHERE email = 'merchant.one@example.com'
        )
    ),
    (
        'key2',
        (
            SELECT id
            FROM merchant
            WHERE email = 'merchant.two@example.com'
        )
    ),
    (
        'key3',
        (
            SELECT id
            FROM merchant
            WHERE email = 'merchant.three@example.com'
        )
    );
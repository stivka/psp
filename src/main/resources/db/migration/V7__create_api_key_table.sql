CREATE SEQUENCE api_key_seq START 1;

CREATE TABLE api_key (
    id BIGINT DEFAULT nextval('api_key_seq') PRIMARY KEY,
    key_value VARCHAR(255) NOT NULL,
    merchant_id BIGINT,
    key_role VARCHAR(255) NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

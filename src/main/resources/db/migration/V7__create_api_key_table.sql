CREATE SEQUENCE api_key_seq START 1;

CREATE TABLE api_key (
    id BIGINT DEFAULT nextval('api_key_seq') PRIMARY KEY,
    api_key VARCHAR(255) NOT NULL,
    merchant_id BIGINT NOT NULL
);

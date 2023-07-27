CREATE SEQUENCE api_key_seq START 1;

CREATE TABLE api_key (
    id BIGINT DEFAULT nextval('api_key_seq') PRIMARY KEY,
    key_value VARCHAR(255) NOT NULL,
    created_by VARCHAR(255) NOT NULL,
    creation_date TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

ALTER TABLE app_user ADD CONSTRAINT fk_app_user_api_key_id FOREIGN KEY (api_key_id) REFERENCES api_key(id);

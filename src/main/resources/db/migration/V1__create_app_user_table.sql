CREATE SEQUENCE app_user_seq START 1;

CREATE TABLE app_user (
    id BIGINT DEFAULT nextval('app_user_seq') PRIMARY KEY,
    full_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    user_password VARCHAR(255) NOT NULL,
    api_key_id BIGINT,
    created_by VARCHAR(255),
    creation_date TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    last_modified_by VARCHAR(255),
    last_modified_date TIMESTAMP, 
    user_type VARCHAR(255) -- added for trying to solve a Hibernate mapping problem
);
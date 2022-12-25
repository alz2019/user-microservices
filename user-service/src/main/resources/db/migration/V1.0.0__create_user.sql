CREATE TABLE users
(
    id         UUID,
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    CONSTRAINT PK_user_copy PRIMARY KEY (id)
);
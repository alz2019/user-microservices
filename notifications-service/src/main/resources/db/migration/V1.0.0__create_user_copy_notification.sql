CREATE TABLE user_copies
(
    id         VARCHAR(36),
    first_name VARCHAR(255),
    last_name  VARCHAR(255),
    CONSTRAINT PK_user_copy PRIMARY KEY (id)
);

CREATE TABLE notifications
(
    id      VARCHAR(36),
    title   VARCHAR(255),
    body    VARCHAR(255),
    user_id VARCHAR(36) NOT NULL,
    CONSTRAINT PK_notification PRIMARY KEY (id),
    CONSTRAINT FK_notification_user_copy FOREIGN KEY (user_id) REFERENCES user_copies (id)
);
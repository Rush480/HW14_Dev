CREATE SEQUENCE IF NOT EXISTS notes_seq
    START WITH 1
    INCREMENT BY 1;

CREATE SEQUENCE IF NOT EXISTS users_seq
    START WITH 1
    INCREMENT BY 1;
CREATE TABLE IF NOT EXISTS users
(
    id       BIGINT DEFAULT nextval('users_seq') PRIMARY KEY,
    username VARCHAR(50) UNIQUE NOT NULL,
    email    VARCHAR(50) UNIQUE NOT NULL,
    enabled  BOOLEAN            NOT NULL DEFAULT TRUE,
    password VARCHAR(255)       NOT NULL

);

CREATE TABLE IF NOT EXISTS notes
(
    id      BIGINT DEFAULT nextval('notes_seq') PRIMARY KEY,
    user_id BIGINT       NOT NULL,
    title   VARCHAR(255) NOT NULL,
    content TEXT         NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users (id) ON DELETE CASCADE
);


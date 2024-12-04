CREATE TABLE IF NOT EXISTS users
(
    id       SERIAL PRIMARY KEY,
    username VARCHAR(50) UNIQUE                                    NOT NULL,
    password VARCHAR(50) CHECK (LENGTH(password) BETWEEN 8 AND 20) NOT NULL,
    roles    VARCHAR(50)                                           NOT NULL
);
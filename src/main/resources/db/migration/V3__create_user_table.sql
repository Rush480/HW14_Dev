CREATE TABLE IF NOT EXISTS users
(
    username VARCHAR(50) UNIQUE NOT NULL PRIMARY KEY,
    password VARCHAR(100)       NOT NULL,
    enabled  BOOLEAN            NOT NULL DEFAULT TRUE
);

CREATE TABLE authorities
(
    username  VARCHAR(50) NOT NULL,
    authority VARCHAR(50) NOT NULL,
    CONSTRAINT fk_authorities_users FOREIGN KEY (username) REFERENCES users (username)
);


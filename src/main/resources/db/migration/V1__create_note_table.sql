CREATE TABLE IF NOT EXISTS note
(
    id      BIGINT PRIMARY KEY,
    title   VARCHAR(255) NOT NULL,
    content TEXT         NOT NULL
);
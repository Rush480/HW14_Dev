CREATE SEQUENCE IF NOT EXISTS note_seq
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS note
(
    id      BIGINT DEFAULT nextval('note_seq') PRIMARY KEY,
    title   VARCHAR(255) NOT NULL,
    content TEXT         NOT NULL
);
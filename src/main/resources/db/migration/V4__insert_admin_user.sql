INSERT INTO users (username, password, enabled)
VALUES ('user', '$2a$10$1ZkdjEl.bYTmpX0kz6NFBeJH.l0DjwdvjRPENMUC6OGq0WA7rUHny', true);

INSERT INTO authorities (username,  authority)
VALUES ('user', 'ROLE_ADMIN');
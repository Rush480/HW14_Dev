CREATE SEQUENCE IF NOT EXISTS seq_roles_id
    START WITH 1
    INCREMENT BY 1;

CREATE TABLE IF NOT EXISTS roles
(
    id   BIGINT DEFAULT nextval('seq_roles_id'),
    name VARCHAR(50) UNIQUE NOT NULL,
    CONSTRAINT pk_roles_id PRIMARY KEY (id)
);

CREATE TABLE IF NOT EXISTS users_roles
(
    user_id BIGINT,
    role_id BIGINT,
    CONSTRAINT pk_users_roles PRIMARY KEY (user_id, role_id),
    CONSTRAINT fk_users_roles_user_id FOREIGN KEY (user_id) REFERENCES users (id),
    CONSTRAINT fk_users_roles_role_id FOREIGN KEY (role_id) REFERENCES roles (id)
);

INSERT INTO roles (id,name)
VALUES (1,'ROLE_USER'),
       (2,'ROLE_ADMIN');



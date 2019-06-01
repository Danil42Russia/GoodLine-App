CREATE TABLE users
(
    id    SERIAL PRIMARY KEY,
    login VARCHAR(32) UNIQUE,
    pass  VARCHAR(64),
    salt  VARCHAR(64)
);

CREATE TABLE roles
(
    id   SERIAL PRIMARY KEY,
    name VARCHAR(32) UNIQUE
);

CREATE TABLE users_roles
(
    id_user INTEGER,
    id_role INTEGER,

    FOREIGN KEY (id_user) REFERENCES users (id),
    FOREIGN KEY (id_role) REFERENCES roles (id)
)
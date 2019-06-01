CREATE TABLE users
(
    id    SERIAL,
    login NVARCHAR(32) UNIQUE,
    pass  NVARCHAR(64),
    salt  NVARCHAR(64)
);

CREATE TABLE roles
(
    id   SERIAL,
    name NVARCHAR(32) UNIQUE
);

CREATE TABLE users_roles
(
    id_user INTEGER,
    id_role INTEGER,

    FOREIGN KEY (id_user) REFERENCES users (id),
    FOREIGN KEY (id_role) REFERENCES roles (id)
)
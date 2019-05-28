CREATE TABLE users
(
    id    INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    login NVARCHAR(32) UNIQUE,
    pass  NVARCHAR(64),
    salt  NVARCHAR(64)
);

CREATE TABLE roles
(
    id   INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    name NVARCHAR(32) UNIQUE
);

CREATE TABLE users_roles
(
    id_user INTEGER,
    id_role INTEGER,

    FOREIGN KEY (id_user) REFERENCES users (id),
    FOREIGN KEY (id_role) REFERENCES roles (id)
)
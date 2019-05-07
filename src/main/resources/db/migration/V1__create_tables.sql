CREATE TABLE user
(
    id    INTEGER PRIMARY KEY AUTOINCREMENT,
    login TEXT UNIQUE,
    pass  TEXT,
    salt  TEXT
);

CREATE TABLE roles
(
    id   INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT UNIQUE
);

CREATE TABLE users_roles
(
    id_user INTEGER,
    id_role INTEGER,

    FOREIGN KEY (id_user) REFERENCES user (id),
    FOREIGN KEY (id_role) REFERENCES roles (id)
)
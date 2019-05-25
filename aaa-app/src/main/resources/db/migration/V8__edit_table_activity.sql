DROP TABLE activity;

CREATE TABLE activity
(
    id    INTEGER PRIMARY KEY AUTOINCREMENT,
    id_ur INTEGER,
    ds    TEXT,
    de    TEXT,
    vol   INTEGER,

    FOREIGN KEY (id_ur) REFERENCES users_roles (id)
);
DROP TABLE activity;

CREATE TABLE activity
(
    id    SERIAL PRIMARY KEY,
    id_ur INTEGER,
    ds    VARCHAR(16),
    de    VARCHAR(16),
    vol   INTEGER,

    FOREIGN KEY (id_ur) REFERENCES users_roles (id)
);
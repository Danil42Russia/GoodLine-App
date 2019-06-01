DROP TABLE activity;

CREATE TABLE activity
(
    id    SERIAL,
    id_ur INTEGER,
    ds    NVARCHAR(16),
    de    NVARCHAR(16),
    vol   INTEGER,

    FOREIGN KEY (id_ur) REFERENCES users_roles (id)
);
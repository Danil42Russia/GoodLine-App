DROP TABLE activity;

CREATE TABLE activity
(
    id    INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    id_ur INTEGER,
    ds    NVARCHAR(16),
    de    NVARCHAR(16),
    vol   INTEGER,

    FOREIGN KEY (id_ur) REFERENCES users_roles (id)
);
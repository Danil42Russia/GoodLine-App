-- noinspection SqlResolve
DROP TABLE accounting;

CREATE TABLE activity
(
    id        INTEGER PRIMARY KEY AUTOINCREMENT,
    id_user   INT,
    res       TEXT,
    roles     TEXT,
    dataStart TEXT,
    dataEnd   TEXT,
    volume    INTEGER,

    FOREIGN KEY (id_user) REFERENCES users (id)
)
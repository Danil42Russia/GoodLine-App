-- noinspection SqlResolve
DROP TABLE accounting;

CREATE TABLE activity
(
    id        SERIAL PRIMARY KEY,
    id_user   INT,
    res       VARCHAR(32),
    roles     VARCHAR(32),
    dataStart VARCHAR(16),
    dataEnd   VARCHAR(16),
    volume    INTEGER,

    FOREIGN KEY (id_user) REFERENCES users (id)
)
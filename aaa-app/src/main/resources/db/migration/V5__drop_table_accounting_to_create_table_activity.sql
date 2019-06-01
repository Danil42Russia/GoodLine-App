-- noinspection SqlResolve
DROP TABLE accounting;

CREATE TABLE activity
(
    id        SERIAL,
    id_user   INT,
    res       NVARCHAR(32),
    roles     NVARCHAR(32),
    dataStart NVARCHAR(16),
    dataEnd   NVARCHAR(16),
    volume    INTEGER,

    FOREIGN KEY (id_user) REFERENCES users (id)
)
CREATE TABLE accounting
(
    id        INTEGER PRIMARY KEY AUTOINCREMENT,
    login     TEXT,
    res       TEXT,
    roles     TEXT,
    dataStart TEXT,
    dataEnd   TEXT,
    volume    INTEGER
)
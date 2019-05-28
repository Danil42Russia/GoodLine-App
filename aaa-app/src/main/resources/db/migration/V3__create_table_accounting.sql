CREATE TABLE accounting
(
    id        INTEGER PRIMARY KEY AUTO_INCREMENT NOT NULL,
    login     NVARCHAR(32),
    res       NVARCHAR(32),
    roles     NVARCHAR(32),
    dataStart NVARCHAR(16),
    dataEnd   NVARCHAR(16),
    volume    INTEGER
)
CREATE TABLE accounting
(
    id        SERIAL,
    login     VARCHAR(32),
    res       VARCHAR(32),
    roles     VARCHAR(32),
    dataStart VARCHAR(16),
    dataEnd   VARCHAR(16),
    volume    INTEGER
)
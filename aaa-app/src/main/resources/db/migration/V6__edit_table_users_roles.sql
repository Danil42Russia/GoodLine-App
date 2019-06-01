create table users_roles_dg_tmp
(
    id      SERIAL PRIMARY KEY,
    id_user INTEGER,
    id_role INTEGER,
    res     VARCHAR(16),

    FOREIGN KEY (id_user) REFERENCES users (id)
);

INSERT INTO users_roles_dg_tmp(id_user, id_role)
SELECT id_user, id_role
FROM users_roles;

DROP TABLE users_roles;

ALTER TABLE users_roles_dg_tmp
    RENAME TO users_roles;

-- noinspection SqlResolve
UPDATE users_roles
SET res = 'A.B.C'
WHERE id = 1;

-- noinspection SqlResolve
UPDATE users_roles
SET res = 'A.B'
WHERE id = 2;

-- noinspection SqlResolve
UPDATE users_roles
SET res = 'A.B.C.D'
WHERE id = 3;

-- noinspection SqlResolve
UPDATE users_roles
SET res = 'A.B'
WHERE id = 4;
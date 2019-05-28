ALTER TABLE users
    ADD version INTEGER;

ALTER TABLE users_roles
    ADD version INTEGER;

ALTER TABLE activity
    ADD version INTEGER;
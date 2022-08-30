DROP TABLE IF EXISTS persons_roles;

CREATE TABLE IF NOT EXISTS persons_roles
(
    person_credentials_id INT NOT NULL REFERENCES person_credentials(id),
    roles_id INT NOT NULL REFERENCES roles(id)
);
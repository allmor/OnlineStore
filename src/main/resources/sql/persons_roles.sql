DROP TABLE IF EXISTS persons_roles;

CREATE TABLE IF NOT EXISTS persons_roles
(
    person_id INT NOT NULL REFERENCES persons(person_id),
    roles_id INT NOT NULL REFERENCES roles(id)
);
DROP TABLE IF EXISTS person_credentials;

CREATE TABLE IF NOT EXISTS person_credentials
(
    person_id           INT NOT NULL REFERENCES persons(person_id),
    person_email varchar(100) UNIQUE NOT NULL,
    person_password     varchar(256)  NOT NULL
);

INSERT INTO person_credentials(person_email, person_password)
VALUES ('mail1@mail.ru', 'password1'),
       ('mail2@mail.ru', 'password2');
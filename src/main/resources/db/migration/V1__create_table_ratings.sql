CREATE TABLE ratings
(
    company varchar(255) NOT NULL REFERENCES companies ON DELETE CASCADE,
    rating FLOAT NOT NULL,
    inserted_at TIMESTAMP NOT NULL,
    PRIMARY KEY (inserted_at, company)
);
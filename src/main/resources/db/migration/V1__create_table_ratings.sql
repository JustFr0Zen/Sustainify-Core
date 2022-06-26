CREATE TABLE ratings
(
    company varchar(255) NOT NULL REFERENCES companies ON DELETE CASCADE,
    rating INTEGER NOT NULL,
    year INTEGER NOT NULL,
    PRIMARY KEY (year, company)
);
CREATE TABLE ratings
(
    rater varchar(255) KEY NOT NULL REFERENCES users,
    company varchar(255) NOT NULL REFERENCES companies,
    rating INTEGER NOT NULL,
    PRIMARY KEY (rater, company)
);
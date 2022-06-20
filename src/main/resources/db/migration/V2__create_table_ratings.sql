CREATE TABLE ratings
(
    rater varchar(255) NOT NULL REFERENCES users ON DELETE CASCADE,
    company varchar(255) NOT NULL REFERENCES companies ON DELETE CASCADE,
    rating INTEGER NOT NULL,
    PRIMARY KEY (rater, company)
);
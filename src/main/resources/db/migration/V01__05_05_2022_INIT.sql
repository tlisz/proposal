CREATE TABLE Proposals (
    id IDENTITY NOT NULL PRIMARY KEY,
    text VARCHAR(255),
    reason VARCHAR(255),
    state VARCHAR(30)
);
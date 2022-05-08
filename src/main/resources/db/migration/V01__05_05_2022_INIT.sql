CREATE TABLE proposals (
    id IDENTITY NOT NULL PRIMARY KEY,
    name VARCHAR(50),
    text VARCHAR(255),
    reason VARCHAR(255),
    state VARCHAR(30)
);

INSERT INTO proposals(id, name, text, reason, state) VALUES
(1, 'proposal1', 'prop1 text', null, 'CREATED'),
(2, 'proposal2', 'prop2 text', null, 'CREATED'),
(3, 'proposal3', 'prop3 text', null, 'CREATED'),
(4, 'proposal4', 'prop4 text', 'reason', 'DELETED'),
(5, 'proposal5', 'prop5 text', 'reason', 'DELETED'),
(6, 'proposal6', 'prop6 text', 'reason', 'DELETED'),
(7, 'proposal7', 'prop7 text', null, 'VERIFIED'),
(8, 'proposal8', 'prop8 text', null, 'VERIFIED'),
(9, 'proposal9', 'prop9 text', null, 'VERIFIED'),
(10, 'proposal10', 'prop1 text', 'reason', 'REJECTED'),
(11, 'proposal11', 'prop11 text', 'reason', 'REJECTED'),
(12, 'proposal12', 'prop12 text', 'reason', 'REJECTED'),
(13, 'proposal13', 'prop13 text', null, 'ACCEPTED'),
(14, 'proposal14', 'prop14 text', null, 'ACCEPTED'),
(15, 'proposal15', 'prop15 text', null, 'ACCEPTED'),
(16, 'proposal16', 'prop16 text', null, 'PUBLISHED'),
(17, 'proposal17', 'prop17 text', null, 'PUBLISHED'),
(18, 'proposal18', 'prop18 text', null, 'PUBLISHED');

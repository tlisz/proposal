CREATE TABLE state_history (
    id IDENTITY NOT NULL PRIMARY KEY,
    proposal_id INT NOT NULL,
    previous_state VARCHAR(30),
    next_state VARCHAR(30),
    date VARCHAR(255),
    FOREIGN KEY (proposal_id) REFERENCES proposals(id)
);

INSERT INTO state_history(id, proposal_id, previous_state, next_state, date) VALUES
(1, 1, null, 'CREATED', '2022-05-08T14:48:00.000Z'),
(2, 2, null, 'CREATED', '2022-05-08T14:48:00.000Z'),
(3, 3, null, 'CREATED', '2022-05-08T14:48:00.000Z'),
(4, 4, null, 'CREATED', '2022-05-08T14:48:00.000Z'),
(5, 5, null, 'CREATED', '2022-05-08T14:48:00.000Z'),
(6, 6, null, 'CREATED', '2022-05-08T14:48:00.000Z'),
(7, 4, 'CREATED', 'DELETED', '2022-05-08T14:48:00.000Z'),
(8, 5, 'CREATED', 'DELETED', '2022-05-08T14:48:00.000Z'),
(9, 6, 'CREATED', 'DELETED', '2022-05-08T14:48:00.000Z');
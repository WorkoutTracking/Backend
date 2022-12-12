INSERT INTO useraccount (id, createdAt, name, email) VALUES ('473cc7ef-f4a8-4325-bda5-a4847938313c', CURRENT_TIMESTAMP, 'Carlo', 'carlovankessel@yahoo.nl');

INSERT INTO workout (id, createdAt, userEmail, name) VALUES ('32b5646c-cecb-4518-ae17-bcd296990db7', CURRENT_TIMESTAMP, 'carlovankessel@yahoo.nl', 'Push');
INSERT INTO workout (id, createdAt, userEmail, name) VALUES ('d0e244ce-bd2f-4a30-b467-f3930f37e4dc', CURRENT_TIMESTAMP, 'carlovankessel@yahoo.nl', 'Pull');
INSERT INTO workout (id, createdAt, userEmail, name) VALUES ('5498f676-d9ec-4dba-9e16-164dc782bfa5', CURRENT_TIMESTAMP, 'carlovankessel@yahoo.nl', 'Legs');

INSERT INTO exercise (id, createdAt, workoutId, name) VALUES ('120ccfcf-f7ad-424a-82d2-8d0b375c8a6e', CURRENT_TIMESTAMP, '32b5646c-cecb-4518-ae17-bcd296990db7', 'Barbell Bench Press');
INSERT INTO exercise (id, createdAt, workoutId, name) VALUES ('7fd0d588-5769-4ba1-ba09-d0aeb2a1e39c', CURRENT_TIMESTAMP, '32b5646c-cecb-4518-ae17-bcd296990db7', 'Incline Dumbbell Press');
INSERT INTO exercise (id, createdAt, workoutId, name) VALUES ('c06054ae-b691-4506-bd64-86ec85770265', CURRENT_TIMESTAMP, 'd0e244ce-bd2f-4a30-b467-f3930f37e4dc', 'Barbell Row');
INSERT INTO exercise (id, createdAt, workoutId, name) VALUES ('71052396-be20-4b78-bb77-04db066107d4', CURRENT_TIMESTAMP, 'd0e244ce-bd2f-4a30-b467-f3930f37e4dc', 'Pull Up');

INSERT INTO exerciseSet (id, createdAt, exerciseId, sets, reps, weight, rest)
VALUES ('49e5d8cd-559c-43e8-bc63-5124e6f0a743', CURRENT_TIMESTAMP, '120ccfcf-f7ad-424a-82d2-8d0b375c8a6e', 1, 10, 20, 5);
INSERT INTO exerciseSet (id, createdAt, exerciseId, sets, reps, weight, rest)
VALUES ('8cb819a7-5ca3-4b12-8f69-57601ad73db6', CURRENT_TIMESTAMP, '120ccfcf-f7ad-424a-82d2-8d0b375c8a6e', 1, 10, 40, 5);

INSERT INTO exerciseSet (id, createdAt, exerciseId, sets, reps, weight, rest)
VALUES ('a3731bb7-8e6b-4ef9-81e9-12b4b29883ca', CURRENT_TIMESTAMP, '7fd0d588-5769-4ba1-ba09-d0aeb2a1e39c', 1, 10, 12, 5);
INSERT INTO exerciseSet (id, createdAt, exerciseId, sets, reps, weight, rest)
VALUES ('fb9696f7-dae2-4c7b-b57f-fd1637b175fd', CURRENT_TIMESTAMP, '7fd0d588-5769-4ba1-ba09-d0aeb2a1e39c', 1, 10, 20, 5);
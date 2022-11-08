INSERT INTO useraccount (id, created_at, name, email) VALUES ('473cc7ef-f4a8-4325-bda5-a4847938313c', CURRENT_TIMESTAMP, 'Carlo', 'carlovankessel@yahoo.nl');

INSERT INTO workout (id, created_at, user_email, name) VALUES ('32b5646c-cecb-4518-ae17-bcd296990db7', CURRENT_TIMESTAMP, 'carlovankessel@yahoo.nl', 'Push');
INSERT INTO workout (id, created_at, user_email, name) VALUES ('d0e244ce-bd2f-4a30-b467-f3930f37e4dc', CURRENT_TIMESTAMP, 'carlovankessel@yahoo.nl', 'Pull');

INSERT INTO exercise (id, created_at, workout_id, name) VALUES ('7fd0d588-5769-4ba1-ba09-d0aeb2a1e39c', CURRENT_TIMESTAMP, '32b5646c-cecb-4518-ae17-bcd296990db7', 'Incline Dumbbell Press');
INSERT INTO exercise (id, created_at, workout_id, name) VALUES ('120ccfcf-f7ad-424a-82d2-8d0b375c8a6e', CURRENT_TIMESTAMP, '32b5646c-cecb-4518-ae17-bcd296990db7', 'Barbell Bench Press');
INSERT INTO exercise (id, created_at, workout_id, name) VALUES ('c06054ae-b691-4506-bd64-86ec85770265', CURRENT_TIMESTAMP, 'd0e244ce-bd2f-4a30-b467-f3930f37e4dc', 'Barbell Row');
INSERT INTO exercise (id, created_at, workout_id, name) VALUES ('71052396-be20-4b78-bb77-04db066107d4', CURRENT_TIMESTAMP, 'd0e244ce-bd2f-4a30-b467-f3930f37e4dc', 'Pull Up');
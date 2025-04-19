CREATE EXTENSION IF NOT EXISTS "pgcrypto";

DELETE FROM "users"
WHERE username='pinu';

INSERT INTO "users" ("id", "username", "email", "password")
VALUES (
        gen_random_uuid(),
        'pinu',
        'pinu@example.com',
        'pinupass'
       );

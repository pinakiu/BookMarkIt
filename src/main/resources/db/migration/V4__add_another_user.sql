INSERT INTO "users" ("id", "username", "email", "password")
VALUES (
           gen_random_uuid(),
           'chris',
           'chris@example.com',
           'chrispass'
       );
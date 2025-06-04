CREATE TABLE "users" (
     "id" text NOT NULL,
     "username" text,
     "email" text,
     "password" text,
     "created_at" TIMESTAMP,
     CONSTRAINT "users_pkey" PRIMARY KEY ("id")
);

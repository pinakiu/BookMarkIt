DROP TABLE IF EXISTS "users";
CREATE TABLE "users" (
     "id" UUID PRIMARY KEY DEFAULT NULL,
     "username" TEXT NOT NULL UNIQUE,
     "email" TEXT NOT NULL UNIQUE,
     "password" TEXT NOT NULL,
     "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE "books" (
         "id" UUID PRIMARY KEY DEFAULT NULL,
         "google_books_id" TEXT UNIQUE,
         "title" TEXT NOT NULL,
         "author" TEXT,
         "cover_url" TEXT,
         "isbn" TEXT UNIQUE,
         "description" TEXT
);

CREATE TABLE "user_books" (
          "id" UUID PRIMARY KEY DEFAULT NULL,
          "user_id" UUID NOT NULL,
          "book_id" UUID NOT NULL,
          "status" TEXT CHECK ("status" IN ('TO_READ', 'READING', 'READ')),
          "progress" INTEGER DEFAULT 0,
          "rating" FLOAT CHECK ("rating" BETWEEN 0 AND 5),
          "review" TEXT,
          "added_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
          "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
          CONSTRAINT "fk_user_books_user" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE CASCADE,
          CONSTRAINT "fk_user_books_book" FOREIGN KEY ("book_id") REFERENCES "books" ("id") ON DELETE CASCADE
);

CREATE EXTENSION IF NOT EXISTS plpgsql;

CREATE TABLE "reviews" (
       "id" UUID PRIMARY KEY DEFAULT NULL,
       "user_id" UUID NOT NULL,
       "book_id" UUID NOT NULL,
       "rating" FLOAT CHECK ("rating" BETWEEN 0 AND 5),
       "review_text" TEXT,
       "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
       CONSTRAINT "fk_reviews_user" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE CASCADE,
       CONSTRAINT "fk_reviews_book" FOREIGN KEY ("book_id") REFERENCES "books" ("id") ON DELETE CASCADE
);

CREATE TABLE "reading_stats" (
         "id" UUID PRIMARY KEY DEFAULT NULL,
         "user_id" UUID NOT NULL UNIQUE,
         "total_books_read" INTEGER DEFAULT 0,
         "pages_read" INTEGER DEFAULT 0,
         "avg_rating" FLOAT DEFAULT 0 CHECK ("avg_rating" BETWEEN 0 AND 5),
         CONSTRAINT "fk_reading_stats_user" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE CASCADE
);

-- Indexes for better query performance
CREATE INDEX idx_books_google_id ON books(google_books_id);
CREATE INDEX idx_user_books_user ON user_books(user_id);
CREATE INDEX idx_user_books_book ON user_books(book_id);
CREATE INDEX idx_reviews_user ON reviews(user_id);
CREATE INDEX idx_reviews_book ON reviews(book_id);
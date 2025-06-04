ALTER TABLE books
RENAME COLUMN id TO market_id;

ALTER TABLE books
ADD cover_url_backup TEXT,
ADD shelves TEXT[],
ADD my_rating INT,
ADD my_review TEXT,
ADD date_start TIMESTAMP,
ADD date_finish TIMESTAMP

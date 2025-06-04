-- Step 1: Add a new temp column with the right type
ALTER TABLE users ADD COLUMN created_at_tmp TIMESTAMP;

-- Step 2: Copy existing values over with casting
UPDATE users SET created_at_tmp = created_at::timestamp;

-- Step 3: Drop the old column
ALTER TABLE users DROP COLUMN created_at;

-- Step 4: Rename the new column to original name
ALTER TABLE users RENAME COLUMN created_at_tmp TO created_at;

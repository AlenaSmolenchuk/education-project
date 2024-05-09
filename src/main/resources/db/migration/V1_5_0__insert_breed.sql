-- create random values for names
CREATE TEMP TABLE IF NOT EXISTS common_breed_names (name VARCHAR(50));

INSERT INTO common_breed_names (name)
VALUES
    ('BLACK'), ('WHITE'), ('RED'), ('BROWN'), ('BLACK&WHITE'), ('BROWN&WHITE');

-- insert random values into table animals.animal
INSERT INTO animals.breed (name)
SELECT
    name
FROM common_breed_names
ORDER BY random()
    LIMIT 6;

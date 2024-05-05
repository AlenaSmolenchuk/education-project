-- create random values for names
CREATE TEMP TABLE IF NOT EXISTS common_animal_names (name VARCHAR(50));

INSERT INTO common_animal_names (name) VALUES
                                           ('Buddy'), ('Max'), ('Bella'), ('Daisy'), ('Charlie'),
                                           ('Molly'), ('Lucy'), ('Sadie'), ('Cooper'), ('Duke'),
                                           ('Luna'), ('Stella'), ('Bear'), ('Rocky'), ('Roxy');

-- insert random values into table animals.animal
INSERT INTO animals.animal (name, id_type, id_breed, age)
SELECT
    name,
    trunc(random() * 10) + 1,
    trunc(random() * 5) + 1,
    trunc(random() * 20) + 1
FROM common_animal_names
ORDER BY random()
    LIMIT 20;

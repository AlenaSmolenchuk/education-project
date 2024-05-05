-- create random values for types
CREATE TEMP TABLE IF NOT EXISTS common_animal_types (type VARCHAR(50));

INSERT INTO common_animal_types (type) VALUES
                                           ('Dog'), ('Cat'), ('Hamster'), ('Wolf'), ('Shark'),
                                           ('Lion'), ('Puma'), ('Crocodile'), ('Elephant'), ('Mouse');

-- insert random values into table animals.animal_type
INSERT INTO animals.animal_type (type, is_wild)
SELECT
    type,
    (random() > 0.5)::boolean
FROM common_animal_types
ORDER BY random()
    LIMIT 10;

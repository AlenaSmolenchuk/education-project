-- create random values for habitats
CREATE TEMP TABLE IF NOT EXISTS common_habitats (area TEXT);

INSERT INTO common_habitats (area) VALUES
                                       ('South-Africa'), ('South-America'), ('America'), ('Asia'), ('Middle-Europe'),
                                       ('Europe'), ('Antarctica'), ('West-Europe');

-- insert random values into table animals.habitat
INSERT INTO animals.habitat (area)
SELECT
    area
FROM common_habitats
ORDER BY random()
    LIMIT 10;

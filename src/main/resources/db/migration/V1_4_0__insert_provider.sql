-- create random values for name_provider
CREATE TEMP TABLE IF NOT EXISTS common_name_provider (name TEXT);

INSERT INTO common_name_provider (name) VALUES
                                            ('Zoo10'), ('Lalaland'), ('Disney'), ('Number1'), ('FunnyZoo');

-- insert random values into table animals.provider
INSERT INTO animals.provider (name, phone)
SELECT
    name,
    concat('+', floor(random()*10000000000)::bigint)
FROM common_name_provider
ORDER BY random()
    LIMIT 10;

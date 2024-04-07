create schema animals;

CREATE TABLE animals.creature (
                          id_creature BIGINT PRIMARY KEY,
                          name TEXT NOT NULL ,
                          type_id INTEGER NOT NULL ,
                          age SMALLINT NOT NULL
);

CREATE TABLE animals.animal_type (
                             id_type INTEGER PRIMARY KEY,
                             type VARCHAR(50) NOT NULL ,
                             is_wild BOOLEAN NOT NULL
);
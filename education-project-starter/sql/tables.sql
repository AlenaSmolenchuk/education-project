create schema animals;

CREATE TABLE animals.creature (
                                  id_creature BIGINT PRIMARY KEY,
                                  name TEXT NOT NULL ,
                                  type_id INTEGER NOT NULL references animals.animal_type(id_type),
                                  age SMALLINT NOT NULL
);

CREATE TABLE animals.animal_type (
                                     id_type INTEGER PRIMARY KEY,
                                     type VARCHAR(50) NOT NULL ,
                                     is_wild BOOLEAN NOT NULL
);

CREATE TABLE animals.habitat (
                                 id_area INTEGER PRIMARY KEY,
                                 area TEXT NOT NULL
);

CREATE TABLE animals.animals_habitats (
                                          id_animal_type INTEGER NOT NULL references animals.animal_type(id_type),
                                          area_id INTEGER NOT NULL references animals.habitat(id_area)
);

CREATE TABLE animals.provider (
                                  id_provider INTEGER PRIMARY KEY,
                                  name TEXT NOT NULL,
                                  phone VARCHAR(50) NOT NULL
);

CREATE TABLE animals.animals_provider (
                                          id_animal_type INTEGER NOT NULL references animals.animal_type(id_type),
                                          provider_id INTEGER NOT NULL references animals.provider(id_provider)
);

alter table animals.animals_habitats
    add constraint id_animal_type_area_pk
        primary key (id_animal_type,area_id);

alter table animals.animals_provider
    add constraint id_animal_type_provider_pk
        primary key (id_animal_type,provider_id);


-- data for animals.animal_type
INSERT INTO animals.animal_type(type, is_wild) VALUES ('Dog', false);
INSERT INTO animals.animal_type(type, is_wild) VALUES ('Shark', true);
INSERT INTO animals.animal_type(type, is_wild) VALUES ('Wolf', true);

-- data for animals.habitat
INSERT INTO animals.habitat (area) VALUES ('Africa');
INSERT INTO animals.habitat (area) VALUES ('America');
INSERT INTO animals.habitat (area) VALUES ('Asia');
INSERT INTO animals.habitat (area) VALUES ('Europe');
INSERT INTO animals.habitat (area) VALUES ('Antarctica');

-- data for animals.provider
INSERT INTO animals.provider (name, phone) VALUES ('Zoo1', '8-999-222-33-44');
INSERT INTO animals.provider (name, phone) VALUES ('Zoo-Animal', '8-987-111-22-33');
INSERT INTO animals.provider (name, phone) VALUES ('Zoo-Friends', '8-965-333-66-87');

-- data for animals.animal
INSERT INTO animals.animal(name, id_type, age) VALUES ('Tom', 1, 10);
INSERT INTO animals.animal(name, id_type, age) VALUES ('Shon', 2, 5);
INSERT INTO animals.animal(name, id_type, age) VALUES ('Lusy', 3, 13);

-- data for animals.animal_habitats
INSERT INTO animals.animal_habitats (id_animal_type, id_area) VALUES (1, 4);
INSERT INTO animals.animal_habitats (id_animal_type, id_area) VALUES (2, 1);
INSERT INTO animals.animal_habitats (id_animal_type, id_area) VALUES (3, 3);
INSERT INTO animals.animal_habitats (id_animal_type, id_area) VALUES (1, 2);

-- data for animals.animal_provider
INSERT INTO animals.animal_provider (id_animal_type, id_provider) VALUES (1, 3);
INSERT INTO animals.animal_provider (id_animal_type, id_provider) VALUES (2, 1);
INSERT INTO animals.animal_provider (id_animal_type, id_provider) VALUES (3, 2);

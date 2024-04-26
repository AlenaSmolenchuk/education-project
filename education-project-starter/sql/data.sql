-- data for animals.animal_type
INSERT INTO animals.animal_type(id_type, type, is_wild) VALUES (1, 'Dog', false);
INSERT INTO animals.animal_type(id_type, type, is_wild) VALUES (2, 'Shark', true);
INSERT INTO animals.animal_type(id_type, type, is_wild) VALUES (3, 'Wolf', true);

-- data for animals.habitat
INSERT INTO animals.habitat (id_area, area) VALUES (1, 'Africa');
INSERT INTO animals.habitat (id_area, area) VALUES (2, 'America');
INSERT INTO animals.habitat (id_area, area) VALUES (3, 'Asia');
INSERT INTO animals.habitat (id_area, area) VALUES (4, 'Europe');
INSERT INTO animals.habitat (id_area, area) VALUES (5, 'Antarctica');

-- data for animals.provider
INSERT INTO animals.provider (id_provider, name, phone) VALUES (1, 'Zoo1', '8-999-222-33-44');
INSERT INTO animals.provider (id_provider, name, phone) VALUES (2, 'Zoo-Animal', '8-987-111-22-33');
INSERT INTO animals.provider (id_provider, name, phone) VALUES (3, 'Zoo-Friends', '8-965-333-66-87');

-- data for animals.creature
INSERT INTO animals.creature(id_creature, name, type_id, age) VALUES (1, 'Tom', 1, 10);
INSERT INTO animals.creature(id_creature, name, type_id, age) VALUES (2, 'Shon', 2, 5);
INSERT INTO animals.creature(id_creature, name, type_id, age) VALUES (3, 'Lusy', 3, 13);

-- data for animals.animals_habitats
INSERT INTO animals.animals_habitats (id_animal_type, area_id) VALUES (1, 4);
INSERT INTO animals.animals_habitats (id_animal_type, area_id) VALUES (2, 1);
INSERT INTO animals.animals_habitats (id_animal_type, area_id) VALUES (3, 3);
INSERT INTO animals.animals_habitats (id_animal_type, area_id) VALUES (1, 2);

-- data for animals.animals_provider
INSERT INTO animals.animals_provider (id_animal_type, provider_id) VALUES (1, 3);
INSERT INTO animals.animals_provider (id_animal_type, provider_id) VALUES (2, 1);
INSERT INTO animals.animals_provider (id_animal_type, provider_id) VALUES (3, 2);
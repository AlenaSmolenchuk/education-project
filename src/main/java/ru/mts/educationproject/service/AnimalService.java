package ru.mts.educationproject.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.mts.educationproject.entity.Animal;
import ru.mts.educationproject.entity.Breed;
import ru.mts.educationproject.repository.dao.AnimalRepository;

import java.util.List;
import java.util.Random;

@Service
public class AnimalService {

    private final AnimalRepository animalRepository;

    public AnimalService(AnimalRepository animalRepository) {
        this.animalRepository = animalRepository;
    }

    public List<Animal> findAll() {
        return animalRepository.findAll();
    }

    public void save(Animal animal) {
        Breed breed = new Breed();
        breed.setIdBreed(new Random().nextInt(1, 6));

        animal.setBreed(breed);
        animalRepository.save(animal);
    }

    @Transactional
    public void delete(int id) {
        animalRepository.deleteByIdAnimal(id);
    }
}

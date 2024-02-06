package ru.mts.repository;

import org.springframework.stereotype.Repository;
import ru.mts.model.animalint.Animal;

import java.util.Set;

@Repository
public interface AnimalsRepository {

    String[] findLeapYearNames();

    Animal[] findOlderAnimal(int age);

    Set<Animal> findDuplicate();

    void printDuplicate();

    void initAnimals();

}

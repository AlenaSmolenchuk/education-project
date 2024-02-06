package ru.mts.repository;

import ru.mts.model.animalint.Animal;

import java.util.Set;


public interface AnimalsRepository {

    String[] findLeapYearNames();

    Animal[] findOlderAnimal(int age);

    Set<Animal> findDuplicate();

    void printDuplicate();

}

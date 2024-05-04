package ru.mts.educationproject.educationprojectstarter.service;

/**
 * Объявление интерфейса CreateAnimalService предоставляет методы для создания уникальных животных.
 */
public interface CreateAnimalService {

    /**
     * Создает n уникальных животных.
     *
     */
    void createAnimals(int n);
}
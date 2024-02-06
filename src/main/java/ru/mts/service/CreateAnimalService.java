package ru.mts.service;

import org.springframework.stereotype.Service;
import ru.mts.model.animalint.Animal;

import java.util.Set;

/**
 * Реализация интерфейса CreateAnimalService предоставляет методы для создания уникальных животных.
 */
@Service
public interface CreateAnimalService {

    /**
     * Создает n уникальных животных.
     *
     * @return массив созданных животных
     */
    Animal[] createAnimals(int n);

    void setAnimalType(String animalType);

}
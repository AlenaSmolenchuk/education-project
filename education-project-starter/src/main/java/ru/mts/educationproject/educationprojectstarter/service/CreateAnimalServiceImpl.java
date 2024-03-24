package ru.mts.educationproject.educationprojectstarter.service;

import ru.mts.educationproject.educationprojectstarter.exceptionst.UnknownAnimalTypeException;
import ru.mts.educationproject.educationprojectstarter.exceptionst.UnknownCountOfAnimalException;
import ru.mts.educationproject.educationprojectstarter.factory.AnimalFactory;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Реализация интерфейса CreateAnimalService для создания животных.
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private final List<AnimalFactory> factories;
    private String animalType;

    /**
     * Конструктор сервиса для создания животных.
     *
     * @param wolfFactory  Фабрика для создания волков.
     * @param dogFactory   Фабрика для создания собак.
     * @param sharkFactory Фабрика для создания акул.
     */
    public CreateAnimalServiceImpl(AnimalFactory wolfFactory,
                                   AnimalFactory dogFactory,
                                   AnimalFactory sharkFactory) {
        this.factories = List.of(wolfFactory, dogFactory, sharkFactory);
    }

    /**
     * Создает n уникальных животных при помощи цикла for.
     *
     * @param n количество животных для создания
     * @return Map, где ключ - тип животного, значение - список созданных животных этого типа
     */
    @Override
    public Map<String, List<Animal>> createAnimals(int n) {
        if (n <= 0) {
            throw new UnknownCountOfAnimalException("The number of animals must be greater than 0.");
        }

        Map<String, List<Animal>> uniqueAnimals = new ConcurrentHashMap<>(n);

        for (int i = 0; i < n; i++) {
            animalType = initializeAnimalType();
            Animal animal = createRandomAnimalByType(animalType);

            if (uniqueAnimals.containsKey(animalType)) {
                uniqueAnimals.get(animalType).add(animal);
            } else {
                List<Animal> animalList = new CopyOnWriteArrayList<>();
                animalList.add(animal);
                uniqueAnimals.put(animalType, animalList);
            }
        }

        return uniqueAnimals;
    }

    /**
     * Определяет тип животного для последующего создания.
     *
     * @return тип животного
     */
    @Override
    public String initializeAnimalType() {
        List<String> availableTypes = new CopyOnWriteArrayList<>(List.of("Wolf", "Shark", "Dog"));

        animalType = availableTypes.get((int) (Math.random() * availableTypes.size()));

        return animalType;
    }

    /**
     * Создает случайное животное в зависимости от текущего установленного типа.
     *
     * @return созданное животное
     * @throws IllegalArgumentException если тип животного неизвестен
     */
    @Override
    public Animal createRandomAnimalByType(String animalType) {
        return switch (animalType.toLowerCase()) {
            case "wolf" -> factories.get(0).createRandomAnimal();
            case "dog" -> factories.get(1).createRandomAnimal();
            case "shark" -> factories.get(2).createRandomAnimal();
            default -> throw new UnknownAnimalTypeException("Unknown animal type: " + animalType);
        };
    }
}
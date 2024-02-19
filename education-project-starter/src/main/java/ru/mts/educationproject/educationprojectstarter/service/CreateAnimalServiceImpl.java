package ru.mts.educationproject.educationprojectstarter.service;

import ru.mts.educationproject.educationprojectstarter.factory.AnimalFactory;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;

import java.util.List;

/**
 * Реализация интерфейса CreateAnimalService для создания животных.
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private List<AnimalFactory> factories;
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
    public CreateAnimalServiceImpl() {

    }

    /**
     * Создает n уникальных животных при помощи цикла do-while.
     *
     * @param n количество животных для создания
     * @return список созданных животных
     */
    @Override
    public Animal[] createAnimals(int n) {

        Animal[] uniqueAnimals = new Animal[n];
        int index = 0;
        do {
            uniqueAnimals[index++] = createRandomAnimalByType(initializeAnimalType());
        } while (index < n);

        return uniqueAnimals;
    }

    /**
     * Определяет тип животного для последующего создания.
     *
     * @return тип животного
     */
    @Override
    public String initializeAnimalType() {
        String[] availableTypes = {"Wolf", "Shark", "Dog"};
        animalType = availableTypes[(int) (Math.random() * availableTypes.length)];

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
            default -> throw new IllegalArgumentException("Unknown animal type: " + animalType);
        };
    }

}
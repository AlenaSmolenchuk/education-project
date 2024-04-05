package ru.mts.educationproject.educationprojectstarter.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;
import ru.mts.educationproject.educationprojectstarter.exceptionst.UnknownAnimalTypeException;
import ru.mts.educationproject.educationprojectstarter.exceptionst.UnknownCountOfAnimalException;
import ru.mts.educationproject.educationprojectstarter.factory.AnimalFactory;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.educationprojectstarter.utilstarter.ConstantsStarter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

import static ru.mts.educationproject.educationprojectstarter.utilstarter.StarterHelper.animalToString;

/**
 * Реализация интерфейса CreateAnimalService для создания животных.
 */
public class CreateAnimalServiceImpl implements CreateAnimalService {

    private static final Logger log = LoggerFactory.getLogger(CreateAnimalServiceImpl.class);
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
        try {
            List<String> lines = new ArrayList<>();
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

                lines.add(animalToString(animal, i + 1));
            }

            Path filePath = ResourceUtils.getFile(ConstantsStarter.LOG_DATA_RESULT).toPath();
            Files.createDirectories(filePath.getParent());
            Files.write(filePath, lines, StandardOpenOption.CREATE,
                    StandardOpenOption.WRITE,
                    StandardOpenOption.TRUNCATE_EXISTING);
        } catch (IOException e) {
            log.error("Something went wrong by writing data in a File: {}", e.getMessage(), e);
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
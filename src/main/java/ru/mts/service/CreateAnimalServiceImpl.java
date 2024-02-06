package ru.mts.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.mts.factory.AnimalFactory;
import ru.mts.factory.DogFactory;
import ru.mts.factory.SharkFactory;
import ru.mts.factory.WolfFactory;
import ru.mts.model.animalint.Animal;

import java.util.HashSet;
import java.util.Set;

/**
 * Реализация интерфейса CreateAnimalService и AnimalFactory для создания животных.
 */
@Component
@Scope("prototype")
public class CreateAnimalServiceImpl implements CreateAnimalService, AnimalFactory {

    private String animalType;
    private AnimalFactory animalFactory;

    @Autowired
    public CreateAnimalServiceImpl(AnimalFactory animalFactory) {
        this.animalFactory = animalFactory;
    }

    @Override
    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    /**
     * Создает n уникальных животных при помощи цикла do-while.
     *
     * @param n количество животных для создания
     * @return список созданных животных
     */
    public Animal[] createAnimals(int n) {

        Animal[] uniqueAnimals = new Animal[n];
        int index = 0;

        do {
            uniqueAnimals[index++] = createRandomAnimal();
        } while (index < n);

        return uniqueAnimals;
    }

    @Override
    public Animal createRandomAnimal() {
        return animalFactory.createRandomAnimal();
    }

    public static class AnimalFactoryImpl implements AnimalFactory {
        @Override
        public Animal createRandomAnimal() {
            String[] breeds = {"Wolf", "Shark", "Dog"};
            String breed = breeds[(int) (Math.random() * breeds.length)];
            return switch (breed) {
                case "Wolf" -> new WolfFactory().createRandomAnimal();
                case "Shark" -> new SharkFactory().createRandomAnimal();
                case "Dog" -> new DogFactory().createRandomAnimal();
                default -> throw new IllegalArgumentException("Unknown breed: " + breed);
            };
        }
    }
}

package ru.mts.factory;

import ru.mts.model.animalint.Animal;

import java.time.LocalDate;

/**
 * Объявление интерфейса фабрики для создания животных.
 */
public interface AnimalFactory {

    /**
     * Создает случайное животное.
     *
     * @return случайное животное
     */
    static Animal createRandomAnimal(){
        String[] breeds = {"Wolf", "Shark", "Dog"};
        String breed = breeds[(int) (Math.random() * breeds.length)];
        return switch (breed) {
            case "Wolf" -> new WolfFactory().createRandomWolf();
            case "Shark" -> new SharkFactory().createRandomShark();
            case "Dog" -> new DogFactory().createRandomDog();
            default -> throw new IllegalArgumentException("Unknown breed: " + breed);
        };
    }

    default LocalDate generateRandomDateOfBirth() {

        // Генерация случайной даты в пределах последних 20 лет
        long minDay = LocalDate.now().minusYears(20).toEpochDay();
        long maxDay = LocalDate.now().toEpochDay();
        long randomDay = minDay + (long) (Math.random() * (maxDay - minDay));

        return LocalDate.ofEpochDay(randomDay);
    }
}
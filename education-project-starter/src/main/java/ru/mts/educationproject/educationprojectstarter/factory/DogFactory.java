package ru.mts.educationproject.educationprojectstarter.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.educationprojectstarter.model.animals.Dog;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Реализация фабрики для создания собак.
 */
@Component
public class DogFactory implements AnimalFactory {

    @Value("${animal.dog.names}")
    private  String[] dogNames;

    public DogFactory(String[] dogNames) {
        this.dogNames = dogNames;
    }

    public DogFactory () {
    }

    /**
     * Создает случайный объект собаки с указанной вероятностью генерации дубликата.
     *
     * @return Случайно сгенерированный объект собаки или дубликат объекта собаки в зависимости от вероятности.
     */
    @Override
    public Animal createRandomAnimal() {

        double duplicateProbability = 0.6;

        if (Math.random() < duplicateProbability) {
            return createDuplicate();
        } else {
            AnimalBreed breed = AnimalBreed.values()
                    [(int) (Math.random() * AnimalBreed.values().length)];
            String name = getRandomName(dogNames);
            BigDecimal cost = BigDecimal.valueOf(Math.random() * 1000);
            AnimalCharacter character = AnimalCharacter.values()
                    [(int) (Math.random() * AnimalCharacter.values().length)];
            LocalDate dateOfBirth = generateRandomDateOfBirth();

            return new Dog(breed, name, cost, character, dateOfBirth);
        }
    }

    // Создает дубликат объекта собаки с предопределенными значениями.
    private Dog createDuplicate() {
        AnimalBreed breed = AnimalBreed.BROWN;
        String name = "Dog";
        BigDecimal cost = BigDecimal.valueOf(23.87);
        AnimalCharacter character = AnimalCharacter.FRIENDLY;
        LocalDate dateOfBirth = LocalDate.now().minusYears(8);

        return new Dog(breed, name, cost, character, dateOfBirth);
    }
}
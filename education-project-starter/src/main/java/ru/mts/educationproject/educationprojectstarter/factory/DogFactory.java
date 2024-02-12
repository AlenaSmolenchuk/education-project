package ru.mts.educationproject.educationprojectstarter.factory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacter.AnimalCharacter;
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
    private  String dogNames;

    /**
     * Создает случайный объект собаки с указанной вероятностью генерации дубликата.
     *
     * @return Случайно сгенерированный объект собаки или дубликат объекта собаки в зависимости от вероятности.
     */

    public Animal createRandomDog() {

        double duplicateProbability = 0.8;

        if (Math.random() < duplicateProbability) {
            return createDuplicate();
        } else {
            String name = getRandomName(dogNames);
            System.out.println(name);
            BigDecimal cost = BigDecimal.valueOf(Math.random() * 1000);
            AnimalCharacter randomCharacter = AnimalCharacter.values()
                    [(int) (Math.random() * AnimalCharacter.values().length)];
            LocalDate dateOfBirth = generateRandomDateOfBirth();

            return new Dog(name, cost, randomCharacter, dateOfBirth);
        }
    }


    // Создает дубликат объекта собаки с предопределенными значениями.
    private Dog createDuplicate() {
        String name = "Dog";
        BigDecimal cost = BigDecimal.valueOf(23.87);
        AnimalCharacter character = AnimalCharacter.FRIENDLY;
        LocalDate dateOfBirth = LocalDate.now().minusYears(8);

        return new Dog(name, cost, character, dateOfBirth);
    }
}
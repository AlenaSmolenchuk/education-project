package ru.mts.educationproject.educationprojectstarter.model.animals;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animaltypes.Predator;

import java.math.BigDecimal;
import java.time.LocalDate;

/**
 * Класс Shark представляет акулу.
 */
public class Shark extends Predator {

    private static final Logger log = LoggerFactory.getLogger(Shark.class);

    /**
     * Конструктор для создания экземпляра акулы.
     *
     * @param breed       порода акулы
     * @param name        имя акулы
     * @param cost        стоимость акулы
     * @param character   характер акулы
     * @param dateOfBirth день рождения акулы
     */
    public Shark(AnimalBreed breed, String name, BigDecimal cost, AnimalCharacter character, LocalDate dateOfBirth) {
        super(breed, name, cost, character, dateOfBirth, "Shark");
    }

    public Shark() {
        super();
    }

    @Override
    public String toString() {
        return "Shark{" +
                "breed='" + breed + '\'' +
                ", name='" + name + '\'' +
                ", cost=" + cost +
                ", character='" + character + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}


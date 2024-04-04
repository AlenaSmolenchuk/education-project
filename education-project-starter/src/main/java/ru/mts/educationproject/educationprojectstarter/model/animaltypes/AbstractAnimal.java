package ru.mts.educationproject.educationprojectstarter.model.animaltypes;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.educationprojectstarter.utilstarter.Base64Serializer;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 * Абстрактный класс AbstractAnimal реализует интерфейс Animal и предоставляет базовую реализацию методов.
 */
public abstract class AbstractAnimal implements Animal {
    protected AnimalBreed breed;
    protected String name;
    protected BigDecimal cost;
    protected AnimalCharacter character;
    protected LocalDate dateOfBirth;
    protected String type;
    protected String secretInfo;


    private static final Logger log = LoggerFactory.getLogger(AbstractAnimal.class);

    /**
     * Конструктор для создания экземпляра абстрактного животного.
     *
     * @param breed     порода животного
     * @param name      имя животного
     * @param cost      стоимость животного
     * @param character характер животного
     * @param dateOfBirth дата рождения животного
     * @param type тип животного
     * @param secretInfo секретная информация о животном
     */
    public AbstractAnimal(AnimalBreed breed,
                          String name,
                          BigDecimal cost,
                          AnimalCharacter character,
                          LocalDate dateOfBirth,
                          String type,
                          String secretInfo) {
        this.breed = breed;
        this.name = name;
        this.cost = cost.setScale(2, RoundingMode.HALF_UP);
        this.character = character;
        this.dateOfBirth = dateOfBirth;
        this.type = type;
        this.secretInfo = secretInfo;
    }

    /**
     * Пустой конструктор класса AbstractAnimal.
     */
    public AbstractAnimal() {
    }

    // Реализация методов интерфейса Animal
    @Override
    public AnimalBreed getBreed() {
        return breed;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public BigDecimal getCost() {
        return cost;
    }

    @Override
    public AnimalCharacter getCharacter() {
        return character;
    }

    @Override
    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    @Override
    public String getType() {
        return type;
    }

    @Override
    @JsonSerialize(using = Base64Serializer.class)
    public String getSecretInfo() {
        return secretInfo;
    }

    public void setBreed(AnimalBreed breed) {
        this.breed = breed;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    public void setCharacter(AnimalCharacter character) {
        this.character = character;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setSecretInfo(String secretInfo) {
        this.secretInfo = secretInfo;
    }

    // Переопределение метода equals()
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AbstractAnimal that = (AbstractAnimal) o;
        return Objects.equals(breed, that.breed)
                && Objects.equals(name, that.name)
                && Objects.equals(cost, that.cost)
                && character == that.character
                && Objects.equals(dateOfBirth, that.dateOfBirth)
                && Objects.equals(type, that.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, name, cost, character, dateOfBirth, type);
    }

}

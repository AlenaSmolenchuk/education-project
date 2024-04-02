package ru.mts.educationproject.educationprojectstarter.model.animaltypes;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import ru.mts.educationproject.educationprojectstarter.exceptionst.EmptyFileStarterException;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalBreed;
import ru.mts.educationproject.educationprojectstarter.model.animalcharacteristic.AnimalCharacter;
import ru.mts.educationproject.educationprojectstarter.model.animalint.Animal;
import ru.mts.educationproject.educationprojectstarter.utilstarter.ConstantsStarter;

import java.io.Externalizable;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Objects;
import java.util.Random;

/**
 * Абстрактный класс AbstractAnimal реализует интерфейс Animal и предоставляет базовую реализацию методов.
 */
public abstract class AbstractAnimal implements Animal, Externalizable {
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
    public String getSecretInfo() {
        try {
            List<String> secretInfo = Files
                    .readAllLines(Path.of(ConstantsStarter.SECRET_INFORMATION_RESULT));
            if (!secretInfo.isEmpty()) {
                Random rand = new Random();
                int index = rand.nextInt(secretInfo.size());

                return secretInfo.get(index);
            } else {
                throw new EmptyFileStarterException("This file is empty");
            }

        } catch (IOException e) {
            log.error("Something went wrong by by reading data: " + e.getMessage(), e);
            return "Failed to read secret information";
        }
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

    @Override
    public void writeExternal(ObjectOutput out) throws IOException {
        out.writeObject(breed);
        out.writeUTF(name);
        out.writeObject(cost);
        out.writeObject(character);
        out.writeObject(dateOfBirth);
        out.writeUTF(type);
        out.writeUTF(Base64.getEncoder().encodeToString(secretInfo.getBytes()));
    }

    @Override
    public void readExternal(ObjectInput in) throws IOException, ClassNotFoundException {
        this.breed = (AnimalBreed) in.readObject();
        this.name = in.readUTF();
        this.cost = (BigDecimal) in.readObject();
        this.character = (AnimalCharacter) in.readObject();
        this.dateOfBirth = (LocalDate) in.readObject();
        this.type = in.readUTF();
        this.secretInfo= new String(Base64.getDecoder().decode(in.readUTF()));
    }
}

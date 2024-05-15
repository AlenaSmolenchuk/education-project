package ru.mts.educationproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;


@Getter
@Setter
@Entity
@Table(schema = "animals", name = "animal")
public class Animal {

    @Id
    @Column(name = "id_animal")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnimal;

    @Column(name = "name")
    @NotBlank(message = "Name should not be empty")
    @Size(max = 50, message = "Name cannot be longer than 50 characters")
    @Pattern(regexp = "\\D+", message = "Name cannot be a number")
    private String name;

    @ManyToOne(targetEntity = AnimalType.class, cascade = CascadeType.ALL)
    @JoinColumn(name="id_type")
    @NotNull(message = "Type should not be empty")
    private AnimalType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_breed")
    @NotNull(message = "Breed should not be empty")
    private Breed breed;

    @Column(name = "age")
    @NotNull(message = "Age should not be empty")
    @Min(value = 0, message = "Age cannot be negative")
    @Max(value = 30, message = "Age cannot be greater than 30")
    private Short age;

    public Animal(String name, AnimalType type, Breed breed, short age) {
        this.name = name;
        this.type = type;
        this.breed = breed;
        this.age = age;
    }

    public Animal() {}


    public Animal(Integer idAnimal, String name, Short age) {
        this.idAnimal = idAnimal;
        this.name = name;
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(breed, animal.breed) && Objects.equals(type, animal.type) && Objects.equals(name, animal.name) && Objects.equals(age, animal.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(breed, type, name, age);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "idAnimal=" + idAnimal +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

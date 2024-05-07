package ru.mts.educationproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
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
    private String name;

    @ManyToOne(targetEntity = AnimalType.class, cascade = CascadeType.ALL)
    @JoinColumn(name="id_type")
    private AnimalType type;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_breed")
    private Breed breed;

    @Column(name = "age")
    private Short age;

    public Animal(String name, AnimalType type, short age) {
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public Animal() {}

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

package ru.mts.educationproject.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "animals", name = "animal")
public class Animal {

    @Id
    @Column(name = "id_animal")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idAnimal;

    @Column(name = "name")
    private String name;

    @OneToOne
    @JoinColumn(name="id_type")
    private AnimalType type;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_breed")
    private Breed breed;

    @Column(name = "age")
    private Short age;

    public Animal(String name, AnimalType type, short age) {
        this.name = name;
        this.type = type;
        this.age = age;
    }

    public Animal() {
    }

    public Integer getIdAnimal() {
        return idAnimal;
    }

    public void setIdAnimal(Integer idAnimal) {
        this.idAnimal = idAnimal;
    }

    public Breed getBreed() {
        return breed;
    }

    public void setBreed(Breed breed) {
        this.breed = breed;
    }

    public AnimalType getType() {
        return type;
    }

    public void setType(AnimalType type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Animal animal = (Animal) o;
        return Objects.equals(idAnimal, animal.idAnimal) && Objects.equals(breed, animal.breed) && Objects.equals(type, animal.type) && Objects.equals(name, animal.name) && Objects.equals(age, animal.age);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAnimal, breed, type, name, age);
    }

    @Override
    public String toString() {
        return "Animal{" +
                "idAnimal=" + idAnimal +
                ", breed=" + breed +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

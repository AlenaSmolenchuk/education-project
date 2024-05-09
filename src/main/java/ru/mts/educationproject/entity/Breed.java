package ru.mts.educationproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.util.List;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(schema = "animals", name = "breed")
public class Breed {

    @Id
    @Column(name = "id_breed")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBreed;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "breed", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Animal> animals;

    public Breed() {}

    public Breed(String name, List<Animal> animals) {
        this.name = name;
        this.animals = animals;
        for (Animal animal : animals) {
            animal.setBreed(this);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Breed breed = (Breed) o;
        return Objects.equals(name, breed.name) && Objects.equals(animals, breed.animals);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, animals);
    }

    @Override
    public String toString() {
        return "Breed{" +
                "idBreed=" + idBreed +
                ", name='" + name + '\'' +
                ", animals=" + animals +
                '}';
    }
}

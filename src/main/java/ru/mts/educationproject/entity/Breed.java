package ru.mts.educationproject.entity;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.util.List;

@Entity
@Table(schema = "animals", name = "breed")
public class Breed {

    @Id
    @Column(name = "id_breed")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idBreed;

    @Column(name = "name")
    private String name;

    @OneToMany(mappedBy = "breed", fetch = FetchType.EAGER)
    private List<Animal> animals;

    public Breed() {
    }

    public Breed(String name, List<Animal> animals) {
        this.name = name;
        this.animals = animals;
        for (Animal animal : animals) {
            animal.setBreed(this);
        }
    }

    public Integer getIdBreed() {
        return idBreed;
    }

    public void setIdBreed(Integer idBreed) {
        this.idBreed = idBreed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public void setAnimals(List<Animal> animals) {
        this.animals = animals;
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

package ru.mts.educationproject.educationprojectstarter.entity;

import java.util.List;

public class Habitat {

    private Integer idArea;

    private String area;

    private List<AnimalType> animalTypes;

    public Habitat() {
    }

    public Habitat(Integer idArea, String area, List<AnimalType> animalTypes) {
        this.idArea = idArea;
        this.area = area;
        this.animalTypes = animalTypes;
    }

    public Integer getIdArea() {
        return idArea;
    }

    public void setIdArea(Integer idArea) {
        this.idArea = idArea;
    }

    public String getArea() {
        return area;
    }

    public List<AnimalType> getAnimalTypes() {
        return animalTypes;
    }

    public void setAnimalTypes(List<AnimalType> animalTypes) {
        this.animalTypes = animalTypes;
    }

    public void setArea(String area) {
        this.area = area;
    }

    @Override
    public String toString() {
        return "Habitat{" +
                "idArea=" + idArea +
                ", area='" + area + '\'' +
                '}';
    }
}

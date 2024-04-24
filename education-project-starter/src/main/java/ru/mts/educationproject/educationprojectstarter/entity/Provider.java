package ru.mts.educationproject.educationprojectstarter.entity;

import java.util.List;

public class Provider {

    private Integer idProvider;

    private String name;

    private String phone;

    private List<AnimalType> animalTypes;

    public Provider() {
    }

    public Provider(Integer idProvider, String name, String phone, List<AnimalType> animalTypes) {
        this.idProvider = idProvider;
        this.name = name;
        this.phone = phone;
        this.animalTypes = animalTypes;
    }

    public Integer getIdProvider() {
        return idProvider;
    }

    public void setIdProvider(Integer idProvider) {
        this.idProvider = idProvider;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<AnimalType> getAnimalTypes() {
        return animalTypes;
    }

    public void setAnimalTypes(List<AnimalType> animalTypes) {
        this.animalTypes = animalTypes;
    }

    @Override
    public String toString() {
        return "Provider{" +
                "idProvider=" + idProvider +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}

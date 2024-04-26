package ru.mts.educationproject.educationprojectstarter.entity;

import java.util.List;

public class AnimalType {

    private Integer idType;

    private String type;

    private Boolean isWild;


    private List<Habitat> habitats;

    private List<Provider> providers;

    public AnimalType() {
    }

    public AnimalType(Integer idType, String type, Boolean isWild, List<Habitat> habitats, List<Provider> providers) {
        this.idType = idType;
        this.type = type;
        this.isWild = isWild;
        this.habitats = habitats;
        this.providers = providers;
    }

    public Integer getIdType() {
        return idType;
    }

    public String getType() {
        return type;
    }

    public Boolean getWild() {
        return isWild;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public void setType(String type) {
        this.type = type;
    }

    public void setWild(Boolean wild) {
        isWild = wild;
    }

    public List<Habitat> getHabitats() {
        return habitats;
    }

    public void setHabitats(List<Habitat> habitats) {
        this.habitats = habitats;
    }

    public List<Provider> getProviders() {
        return providers;
    }

    public void setProviders(List<Provider> providers) {
        this.providers = providers;
    }

    @Override
    public String toString() {
        return "AnimalType{" +
                "idType=" + idType +
                ", type='" + type + '\'' +
                ", isWild=" + isWild +
                '}';
    }
}

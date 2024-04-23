package ru.mts.educationproject.educationprojectstarter.entity;

public class AnimalType {

    private Integer idType;

    private String type;

    private Boolean isWild;


    public AnimalType() {
    }

    public AnimalType(String type, Boolean isWild) {
        this.idType = idType;
        this.type = type;
        this.isWild = isWild;
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

    @Override
    public String toString() {
        return "AnimalType{" +
                "idType=" + idType +
                ", type='" + type + '\'' +
                ", isWild=" + isWild +
                '}';
    }
}

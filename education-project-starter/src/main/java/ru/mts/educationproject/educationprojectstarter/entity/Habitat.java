package ru.mts.educationproject.educationprojectstarter.entity;

public class Habitat {

    private Integer idArea;

    private String area;


    public Habitat() {
    }

    public Habitat(Integer idArea, String area) {
        this.idArea = idArea;
        this.area = area;
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

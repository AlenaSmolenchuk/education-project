package ru.mts.educationproject.entity;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.util.Objects;

@Entity
@Table(schema = "animals", name = "habitat")
public class Habitat {

    @Id
    @Column(name = "id_area")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idArea;

    @Column(name = "area")
    private String area;

    public Habitat() {
    }

    public Habitat(String area) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitat habitat = (Habitat) o;
        return Objects.equals(idArea, habitat.idArea)
                && Objects.equals(area, habitat.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idArea, area);
    }

    @Override
    public String toString() {
        return "Habitat{" +
                "idArea=" + idArea +
                ", area='" + area + '\'' +
                '}';
    }
}

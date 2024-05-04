package ru.mts.educationproject.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.util.Objects;

@Getter
@Setter
@Entity
@Table(name = "habitat")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Habitat habitat = (Habitat) o;
        return Objects.equals(area, habitat.area);
    }

    @Override
    public int hashCode() {
        return Objects.hash(area);
    }

    @Override
    public String toString() {
        return "Habitat{" +
                "idArea=" + idArea +
                ", area='" + area + '\'' +
                '}';
    }
}

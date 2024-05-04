package ru.mts.educationproject.entity;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "animals", name = "animal_type")
public class AnimalType {

    @Id
    @Column(name = "id_type")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idType;

    @Column(name = "type")
    private String type;

    @Column(name = "is_wild")
    private boolean isWild;


    @ManyToMany(targetEntity = Habitat.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "animal_habitats",
            joinColumns = @JoinColumn(name = "id_animal_type", referencedColumnName = "id_type"),
            inverseJoinColumns = @JoinColumn(name = "area_id", referencedColumnName = "id_area"))
    private List<Habitat> habitats;

    @ManyToMany(targetEntity = Provider.class)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(name = "animal_provider",
            joinColumns = @JoinColumn(name = "id_animal_type", referencedColumnName = "id_type"),
            inverseJoinColumns = @JoinColumn(name = "provider_id", referencedColumnName = "id_provider"))
    private List<Provider> providers;

    public AnimalType() {
    }

    public AnimalType(String type, Boolean isWild, List<Habitat> habitats, List<Provider> providers) {
        this.type = type;
        this.isWild = isWild;
        this.habitats = habitats;
        this.providers = providers;
    }


    public Integer getIdType() {
        return idType;
    }

    public void setIdType(Integer idType) {
        this.idType = idType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isWild() {
        return isWild;
    }

    public void setWild(boolean wild) {
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalType that = (AnimalType) o;
        return isWild == that.isWild && Objects.equals(idType, that.idType) && Objects.equals(type, that.type) && Objects.equals(habitats, that.habitats) && Objects.equals(providers, that.providers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idType, type, isWild, habitats, providers);
    }

    @Override
    public String toString() {
        return "AnimalType{" +
                "idType=" + idType +
                ", type='" + type + '\'' +
                ", isWild=" + isWild +
                ", habitats=" + habitats +
                ", providers=" + providers +
                '}';
    }
}

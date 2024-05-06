package ru.mts.educationproject.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

import javax.persistence.*;

import java.util.List;
import java.util.Objects;

@Getter
@Setter
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

    @ManyToMany(targetEntity = Habitat.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(schema = "animals", name = "animal_habitats",
            joinColumns = @JoinColumn(name = "id_animal_type", referencedColumnName = "id_type"),
            inverseJoinColumns = @JoinColumn(name = "id_area", referencedColumnName = "id_area"))
    private List<Habitat> habitats;

    @ManyToMany(targetEntity = Provider.class, cascade = CascadeType.ALL)
    @LazyCollection(LazyCollectionOption.FALSE)
    @JoinTable(schema = "animals", name = "animal_provider",
            joinColumns = @JoinColumn(name = "id_animal_type", referencedColumnName = "id_type"),
            inverseJoinColumns = @JoinColumn(name = "id_provider", referencedColumnName = "id_provider"))
    private List<Provider> providers;

    public AnimalType() {
    }

    public AnimalType(String type,
                      Boolean isWild,
                      List<Habitat> habitats,
                      List<Provider> providers) {
        this.type = type;
        this.isWild = isWild;
        this.habitats = habitats;
        this.providers = providers;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AnimalType that = (AnimalType) o;
        return isWild == that.isWild && Objects.equals(type, that.type) && Objects.equals(habitats, that.habitats) && Objects.equals(providers, that.providers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(type, isWild, habitats, providers);
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

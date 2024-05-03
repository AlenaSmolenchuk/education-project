package ru.mts.educationproject.entity;

import javax.persistence.*;
import javax.persistence.GenerationType;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "animals", name = "provider")
public class Provider {

    @Id
    @Column(name = "id_provider")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProvider;

    @Column(name = "name")
    private String name;

    @Column(name = "phone")
    private String phone;

    public Provider() {
    }

    public Provider(String name, String phone) {
        this.name = name;
        this.phone = phone;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Provider provider = (Provider) o;
        return Objects.equals(idProvider, provider.idProvider)
                && Objects.equals(name, provider.name)
                && Objects.equals(phone, provider.phone);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idProvider, name, phone);
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

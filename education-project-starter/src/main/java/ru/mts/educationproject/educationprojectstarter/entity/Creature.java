package ru.mts.educationproject.educationprojectstarter.entity;

public class Creature {

    private Long idCreature;

    private String name;

    private Integer typeId;

    private Short age;

    public Creature() {
    }

    public Creature(Long idCreature, String name, Integer typeId, Short age) {
        this.idCreature = idCreature;
        this.name = name;
        this.typeId = typeId;
        this.age = age;
    }

    public Long getIdCreature() {
        return idCreature;
    }

    public void setIdCreature(Long idCreature) {
        this.idCreature = idCreature;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTypeId() {
        return typeId;
    }

    public void setTypeId(Integer typeId) {
        this.typeId = typeId;
    }

    public Short getAge() {
        return age;
    }

    public void setAge(Short age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Creature{" +
                "idCreature=" + idCreature +
                ", name='" + name + '\'' +
                ", typeId=" + typeId +
                ", age=" + age +
                '}';
    }
}

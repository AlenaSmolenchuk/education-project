package ru.mts.dto;

public class House {

    public int number;
    protected  String address;

    int floors;

    private String material;

    @Override
    public String toString() {
        return "House{" +
                "number=" + number +
                ", address='" + address + '\'' +
                ", floors=" + floors +
                ", material='" + material + '\'' +
                '}';
    }

    public void mainMethod() {

    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getFloors() {
        return floors;
    }

    public void setFloors(int floors) {
        this.floors = floors;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }
}

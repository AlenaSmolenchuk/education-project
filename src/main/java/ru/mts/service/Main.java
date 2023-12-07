package ru.mts.service;

import ru.mts.dto.House;

public class Main {
    public static void main(String[] args) {

        House woodHouse = new House();
        woodHouse.setMaterial("wood");

        updateMaterial(woodHouse);
        System.out.println(woodHouse);

    }

    public static void updateMaterial(House house) {
        house.setMaterial("lflfl");

        house = new House();
        house.setMaterial("hehehe");
    }
}

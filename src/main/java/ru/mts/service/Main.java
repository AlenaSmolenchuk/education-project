package ru.mts.service;

import ru.mts.dao.Calculating;
import ru.mts.model.Purchase;

public class Main {

    // Создание объекта Calculating для вызова методов calculateTotalWithoutDiscount() и calculateTotalWithDiscount
    private static Calculating calculate;

    public static void main(String[] args) {

        // Создание объекта Purchase с 10 товарами, суммой 20 и скидкой 0.75%
        Purchase purchaseFirst = new Purchase(10, 20, 0.75);
        calculate.calculateTotalWithoutDiscount(purchaseFirst);
        calculate.calculateTotalWithDiscount(purchaseFirst);

        // Создание объекта Purchase с 5 товарами, суммой 30 и скидкой 42.575%
        Purchase purchaseSecond = new Purchase(5, 30, 42.575);
        calculate.calculateTotalWithoutDiscount(purchaseSecond);
        calculate.calculateTotalWithDiscount(purchaseSecond);

        // Создание объекта Purchase с 8 товарами, суммой 15 и скидкой 59.1%
        Purchase purchaseThird = new Purchase(8, 15, 59.1);
        calculate.calculateTotalWithoutDiscount(purchaseThird);
        calculate.calculateTotalWithDiscount(purchaseThird);
    }
}

package ru.mts.service;

import ru.mts.model.Purchase;

public class Main {

    /**
     * Метод для выполнения расчетов для объекта Purchase.
     *
     * @param purchase Объект Purchase
     */
    public static String performCalculations(Purchase purchase) {

        return purchase.calculateTotalWithoutDiscount()
                + " (1)    "
                + String.format("%.2f", purchase.calculateTotalWithDiscount())
                + " (2)  ";
    }

    public static void main(String[] args) {

        // Создание объектов Purchase с различными параметрами
        Purchase purchaseFirst = new Purchase(10, 20, 0.75);
        Purchase purchaseSecond = new Purchase(5, 30, 42.575);
        Purchase purchaseThird = new Purchase(8, 15, 59.1);

        // Вывод результатов на экран
        System.out.println("Общая сумма покупки первого товара без скидки(1) и со скидкой(2): "
                + performCalculations(purchaseFirst));
        System.out.println("Общая сумма покупки второго товара без скидки(1) и со скидкой(2): "
                + performCalculations(purchaseSecond));
        System.out.println("Общая сумма покупки третьего товара без скидки(1) и со скидкой(2): "
                + performCalculations(purchaseThird));
    }
}

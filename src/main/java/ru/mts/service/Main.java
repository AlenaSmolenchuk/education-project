package ru.mts.service;

import ru.mts.model.Purchase;

public class Main {

        /**
         * Метод для выполнения расчетов для объекта Purchase.
         *
         * @param purchase Объект Purchase
         */
        public static void performCalculations(Purchase purchase) {
            purchase.calculateTotalWithoutDiscount();
            purchase.calculateTotalWithDiscount();
            System.out.println();
        }

        public static void main(String[] args) {

            // Создание объектов Purchase с различными параметрами
            Purchase purchaseFirst = new Purchase(10, 20, 0.75);
            Purchase purchaseSecond = new Purchase(5, 30, 42.575);
            Purchase purchaseThird = new Purchase(8, 15, 59.1);

            // Выполнение подсчетов для каждого объекта
            performCalculations(purchaseFirst);
            performCalculations(purchaseSecond);
            performCalculations(purchaseThird);
        }
}

package ru.mts.dao;

import ru.mts.model.Purchase;

public class Calculating {

    /**
     * Метод для расчета общей суммы покупки без скидки.
     */
    public void calculateTotalWithoutDiscount(Purchase purchase) {

        // Общая сумма без скидки
        double totalWithoutDiscount = purchase.getQuantity() * purchase.getAmount();

        // Вывод результатов на экран
        System.out.println("Общая сумма покупки без скидки: " + totalWithoutDiscount);
    }

    /**
     * Метод для расчета общей суммы покупки с учетом скидки.
     */
    public void calculateTotalWithDiscount(Purchase purchase) {

        // Сумма со скидкой
        double totalWithDiscount = purchase.getQuantity() * purchase.getAmount() *
                (1 - purchase.getDiscount()/ 100);

        // Вывод результатов на экран
        System.out.println("Общая сумма покупки со скидкой: " + String.format("%.2f", totalWithDiscount));

    }
}

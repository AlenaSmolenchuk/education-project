package ru.mts.model;

public class Purchase {

    // Количество товаров
    private int quantity;

    // Сумма товара
    private double amount;

    // Скидка на товар
    private double discount;

    /**
     * Конструктор класса Purchase.
     *
     * @param quantity Количество товаров
     * @param amount   Сумма товара
     * @param discount Скидка на товар
     */
    public Purchase(int quantity, double amount, double discount) {
        this.quantity = quantity;
        this.amount = amount;
        this.discount = discount;
    }

    /**
     * Метод для получения значения количества товаров.
     *
     * @return Количество товаров.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Метод для установки значения количества товаров.
     *
     * @param quantity Новое значение количества товаров.
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    /**
     * Метод для получения значения суммы товара.
     *
     * @return Сумма товара.
     */
    public double getAmount() {
        return amount;
    }

    /**
     * Метод для установки значения суммы товара.
     *
     * @param amount Новое значение суммы товара.
     */
    public void setAmount(double amount) {
        this.amount = amount;
    }

    /**
     * Метод для получения значения скидки на товар.
     *
     * @return Скидка на товар.
     */
    public double getDiscount() {
        return discount;
    }

    /**
     * Метод для установки значения скидки на товар.
     *
     * @param discount Новое значение скидки на товар.
     */
    public void setDiscount(double discount) {
        this.discount = discount;
    }

    /**
     * Метод для расчета общей суммы покупки без скидки.
     */
    public double calculateTotalWithoutDiscount() {

        // Общая сумма без скидки
        double totalWithoutDiscount = quantity * amount;

        return totalWithoutDiscount;
    }

    /**
     * Метод для расчета общей суммы покупки с учетом скидки.
     */
    public double calculateTotalWithDiscount() {

        // Сумма со скидкой
        double totalWithDiscount = quantity * amount * (1 - discount/ 100);

        return totalWithDiscount;
    }
}

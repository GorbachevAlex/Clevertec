package main.java.ru.clevertec.check;

class Cards {
    private final int id;
    private final int number;
    private int discountAmount;
    private double balanceDebitCard;

    public Cards(int id, int number, int discountAmount) {
        this.id = id;
        this.number = number;
        this.discountAmount = discountAmount;
    }

    public Cards(int id, int number, int discountAmount, double balanceDebitCard) {
        this.id = id;
        this.number = number;
        this.discountAmount = discountAmount;
        this.balanceDebitCard = balanceDebitCard;
    }

    public int getId() {
        return id;
    }

    public int getNumber() {
        return number;
    }

    public int getDiscountAmount() {
        return discountAmount;
    }

    public double getBalanceDebitCard() {
        return balanceDebitCard;
    }
}

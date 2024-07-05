package main.java.ru.clevertec.check;

import java.util.HashMap;

public class ParserCommand {
    public ParserCommand() {
    }

    private  HashMap<Integer, Integer> productQuantities = new HashMap<>();
    private  int discountCardNumber;
    private  double balanceDebitCard;

    public  void parseInput(String input) {
        String[] arrayStrings = input.split(" ");
        for (String splitString : arrayStrings) {
            if (splitString.startsWith("discountCard=")) {
                discountCardNumber = Integer.parseInt(splitString.substring("discountCard=".length()));
            } else if (splitString.startsWith("balanceDebitCard=")) {
                balanceDebitCard = Double.parseDouble(splitString.substring("balanceDebitCard=".length()));
            } else {
                String[] parts = splitString.split("-");
                int productId = Integer.parseInt(parts[0]);
                int quantity = Integer.parseInt(parts[1]);
                productQuantities.put(productId, quantity);
            }
        }
    }

    public HashMap<Integer, Integer> getProductQuantities() {
        return productQuantities;
    }

    public int getDiscountCardNumber() {
        return discountCardNumber;
    }

    public double getBalanceDebitCard() {
        return balanceDebitCard;
    }

    @Override
    public String toString() {
        return  "productQuantities=" + productQuantities +
                ", discountCardNumber=" + discountCardNumber +
                ", balanceDebitCard=" + balanceDebitCard +
                '}';
    }
}


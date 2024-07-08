package main.java.ru.clevertec.check;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

class ParserCommand {
    private static final ArrayList<Product> productQuantities = new ArrayList<>();
    private static int discountCardNumber;
    private static double balanceDebitCard;

    public static void parseInput(String input) {
        String[] arrayStrings = input.split(" ");
        for (String splitString : arrayStrings) {
            if (splitString.startsWith("discountCard=")) {
                discountCardNumber = Integer.parseInt(splitString.substring("discountCard=".length()));
            } else if (splitString.startsWith("balanceDebitCard=")) {
                balanceDebitCard = Double.parseDouble(splitString.substring("balanceDebitCard=".length()));
            } else {
                String[] parts = splitString.split("-");
                int productId = Integer.parseInt(parts[0]);
                int productQuantity = Integer.parseInt(parts[1]);
                productQuantities.add(new Product(productId, productQuantity));
            }
        }
    }

    public static ArrayList<Product> getProductQuantities() {
        return productQuantities;
    }

    public static ArrayList<Product> getCounterQuantitiesProduct(ArrayList<Product> productQuantities) {
        List<Product> summedProducts = productQuantities.stream()
                .collect(Collectors.groupingBy(Product::getProductId,
                        Collectors.summingInt(Product::getProductQuantity)))
                .entrySet().stream()
                .map(entry -> new Product(entry.getKey(), entry.getValue()))
                .toList();

        return new ArrayList<>(summedProducts);
    }

    public static int getDiscountCardNumber() {
        return discountCardNumber;
    }

    public static double getBalanceDebitCard() {
        return balanceDebitCard;
    }
}



package main.java.ru.clevertec.check;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.*;

class RecordingResult {
    private static int discountCard;
    private static int discountPercentage;
    private static String description;
    private static double price;
    private static int quantityProduct;
    private static int productId;
//    private static int countProductQuantity;
    private static double total;
    private static double totalPrice;
    private static double discount;
    private static double totalDiscount;
    private static double totalWithDiscount;

    private RecordingResult() {
    }

    public static void showResultCSVFile(ArrayList<Product> product, int discountCardNumber, double balanceDebitCard)
            throws IOException {
        HashMap<Integer, Cards> cards = DiscountCardsLoader.getCards();
        HashMap<Integer, Product> products = ProductsLoader.getProducts();
//        ArrayList<Product> counterQuantitiesProduct = ParserCommand.getCounterQuantitiesProduct(product);
//        for (Product count : counterQuantitiesProduct) {
//            countProductQuantity = count.getProductQuantity();
//        }
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        DecimalFormat decimalFormat = new DecimalFormat("0.00$");
        File resultCSV = Path.of("result.scv").toFile();

        try (FileWriter writer = new FileWriter(resultCSV)) {

            writer.append("Date;Time\n")
                    .append(dateFormat.format(new Date())).append(";")
                    .append(timeFormat.format(new Date())).append(";\n\n");

            writer.append("QTY;DESCRIPTION;PRICE;DISCOUNT;TOTAL\n");
            for (Product prod : product) {
                getOriginPercentage(discountCardNumber, cards);
                productId = prod.getProductId();
                quantityProduct = prod.getProductQuantity();
                writer.append(String.valueOf(quantityProduct)).append(";");
                products.forEach((id, values) -> {
                    if (values.getProductId() == productId) {
                        description = values.getDescription();
                        price = values.getPrice();

                        if (values.isWholesaleProduct() && values.getProductQuantity() >= 5) {
                            discountPercentage = 10;
                        }

                        total = price * quantityProduct;
                        discount = total * discountPercentage / 100;
                        getOriginPercentage(discountCardNumber, cards);
                    }
                });
                writer.append(description)
                        .append(";")
                        .append(decimalFormat.format(price))
                        .append(";")
                        .append(decimalFormat.format(discount))
                        .append(";")
                        .append(decimalFormat.format(total)).append("\n");
            }

            writer.append("\nDISCOUNT CARD;DISCOUNT PERCENTAGE\n");
            writer.append(String.valueOf(discountCard)).append(";")
                    .append(String.valueOf(discountPercentage)).append("%\n\n");

            writer.append("TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n");
        }

    }

    private static void getOriginPercentage(int discountCardNumber, HashMap<Integer, Cards> cards) {
        cards.forEach((id, numberCard) -> {
            if (numberCard.getNumber() == discountCardNumber) {
                discountCard = discountCardNumber;
                discountPercentage = numberCard.getDiscountAmount();
            }
        });
    }
}

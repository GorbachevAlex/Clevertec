package main.java.ru.clevertec.check;

import java.io.*;
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
    private static int quantityInStock;
    private static int productId;
    private static double total;
    private static double totalPrice;
    private static double discount;
    private static double totalDiscount;
    private static double totalWithDiscount;
    public static final File resultCSV = Path.of("result.scv").toFile();

    private RecordingResult() {
    }

    public static void showResultCSVFile(ArrayList<Product> product, int discountCardNumber, double balanceDebitCard)
            throws IOException, CustomException {
        HashMap<Integer, Cards> cards = DiscountCardsLoader.getCards();
        HashMap<Integer, Product> products = ProductsLoader.getProducts();
        ArrayList<Product> counterQuantitiesProduct = ParserCommand.getCounterQuantitiesProduct(product);

        SimpleDateFormat dateFormat = new SimpleDateFormat("dd.MM.yyyy");
        SimpleDateFormat timeFormat = new SimpleDateFormat("HH:mm:ss");
        DecimalFormat decimalFormat = new DecimalFormat("0.00$");

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
                for (Map.Entry<Integer, Product> entry : products.entrySet()) {
                    Product values = entry.getValue();
                    if (values.getProductId() == productId) {
                        description = values.getDescription();
                        price = values.getPrice();
                        for (Product count : counterQuantitiesProduct) {
                            if (values.getProductId() == count.getProductId()) {
                                quantityInStock = values.getQuantityInStock() - count.getProductQuantity();
                                if (quantityInStock < count.getQuantityInStock()) {
                                    throw new CustomException(ExceptionMessages.BAD_REQUEST);
                                }
                            }
                            if ((values.getProductId() == count.getProductId()) && values.isWholesaleProduct()
                                    && count.getProductQuantity() >= 5) {
                                discountPercentage = 10;
                                break;
                            }
                        }

                        total = price * quantityProduct;
                        discount = total * discountPercentage / 100;
                        getOriginPercentage(discountCardNumber, cards);
                        totalPrice += total;
                        totalDiscount += discount;
                        totalWithDiscount = totalPrice - totalDiscount;
                    }
                }
                writer.append(description)
                        .append(";")
                        .append(decimalFormat.format(price))
                        .append(";")
                        .append(decimalFormat.format(discount))
                        .append(";")
                        .append(decimalFormat.format(total)).append("\n");
            }
            if (discountPercentage != 0) {
                writer.append("\nDISCOUNT CARD;DISCOUNT PERCENTAGE\n");
                writer.append(String.valueOf(discountCard)).append(";")
                        .append(String.valueOf(discountPercentage)).append("%\n\n");
            } else {
                writer.append("\n");
            }

            writer.append("TOTAL PRICE;TOTAL DISCOUNT;TOTAL WITH DISCOUNT\n")
                    .append(decimalFormat.format((totalPrice)))
                    .append(";")
                    .append(decimalFormat.format(totalDiscount))
                    .append(";")
                    .append(decimalFormat.format(totalWithDiscount))
                    .append(";");
        }
        if (totalWithDiscount > balanceDebitCard) {
            throw new CustomException(ExceptionMessages.NOT_ENOUGH_MONEY);
        }

    }

    private static void getOriginPercentage(int discountCardNumber, HashMap<Integer, Cards> cards) {

        for (Map.Entry<Integer, Cards> entry : cards.entrySet()) {
            Cards numberCard = entry.getValue();
            if (numberCard.getNumber() == discountCardNumber) {
                discountCard = discountCardNumber;
                discountPercentage = numberCard.getDiscountAmount();
                break;
            } else {
                discountPercentage = 0;
            }
        }
    }

    public static void showResultConsole() throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(resultCSV))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        }
    }
}

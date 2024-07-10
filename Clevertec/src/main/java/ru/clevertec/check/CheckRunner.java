package main.java.ru.clevertec.check;

import java.io.IOException;
import java.util.HashMap;
import java.util.Scanner;

public class CheckRunner {
    public static void main(String[] args) throws IOException {

        try (Scanner scanner = new Scanner(System.in)) {
            ParserCommand.parseInput(scanner.nextLine());
            HashMap<Integer, Product> loadProducts = ProductsLoader.loadProducts();
            DiscountCardsLoader.loadDiscountCard();
            ExceptionMessages.validateInput(ParserCommand.getProductQuantities(), loadProducts);
            RecordingResult.showResultCSVFile(
                    ParserCommand.getProductQuantities(),
                    ParserCommand.getDiscountCardNumber(),
                    ParserCommand.getBalanceDebitCard());

        } catch (CustomException | NumberFormatException | ArrayIndexOutOfBoundsException e) {
            ExceptionMessages.handleException(e);
        } finally {
            RecordingResult.showResultConsole();
        }
    }
}
package main.java.ru.clevertec.check;

import java.io.IOException;
import java.util.Scanner;

public class CheckRunner {
    public static void main(String[] args) throws IOException {

        try (Scanner scanner = new Scanner(System.in)) {
            ParserCommand.parseInput(scanner.nextLine());
            ProductsLoader.loadProducts();
            DiscountCardsLoader.loadDiscountCard();
            RecordingResult.showResultCSVFile(ParserCommand.getProductQuantities(), ParserCommand.getDiscountCardNumber(),
                    ParserCommand.getBalanceDebitCard());
        }
    }
}
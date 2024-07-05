package main.java.ru.clevertec.check;

import java.util.Scanner;

public class CheckRunner {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ParserCommand parserCommand = new ParserCommand();
            parserCommand.parseInput(scanner.nextLine());
            System.out.println(parserCommand.toString());
        }
    }
}
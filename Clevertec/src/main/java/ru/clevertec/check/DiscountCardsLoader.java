package main.java.ru.clevertec.check;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.util.HashMap;

class DiscountCardsLoader {
    private static HashMap<Integer, Cards> cards;
    private static final File file = Path.of("src", "main", "resources", "discountCards.csv").toFile();

    private DiscountCardsLoader() {
    }

    static HashMap<Integer, Cards> loadDiscountCard() throws IOException {

        cards = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitString = line.split(";");
                int cardId = Integer.parseInt(splitString[0]);
                int number = Integer.parseInt(splitString[1]);
                int discountAmount = Integer.parseInt(splitString[2]);
                Cards card = new Cards(cardId, number, discountAmount);
                cards.put(cardId, card);
            }
        }
        return cards;
    }

    static HashMap<Integer, Cards> getCards() {
        return cards;
    }
}

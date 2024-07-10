package main.java.ru.clevertec.check;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;

public class ExceptionMessages {
    public static final String BAD_REQUEST = "ERROR\nBAD REQUEST";
    public static final String NOT_ENOUGH_MONEY = "ERROR\nNOT ENOUGH MONEY";
    public static final String INTERNAL_SERVER_ERROR = "ERROR\nINTERNAL SERVER ERROR";

    public static void validateInput(ArrayList<Product> productQuantities, HashMap<Integer, Product> products)
            throws CustomException {
        for (Product product : productQuantities) {
            Product productId = products.get(product.getProductId());
            if (productId == null || product.getProductQuantity() < 0) {
                throw new CustomException(BAD_REQUEST);
            }
        }
    }

    public static void handleException(Exception e) throws IOException {
        String message;
        if (e instanceof CustomException customException) {
            message = customException.getMessageType();
        } else {
            message = INTERNAL_SERVER_ERROR;
        }

        try (FileWriter fileWriter = new FileWriter(RecordingResult.resultCSV);
             PrintWriter printWriter = new PrintWriter(fileWriter)) {
            printWriter.println(message);
        }
    }
}


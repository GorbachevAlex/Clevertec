package main.java.ru.clevertec.check;

import java.io.*;
import java.nio.file.Path;
import java.util.HashMap;

class ProductsLoader {
    private static HashMap<Integer, Product> products;
    private static final File file = Path.of("src", "main", "resources", "products.csv").toFile();

    static HashMap<Integer, Product> loadProducts() throws IOException {

        products = new HashMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            bufferedReader.readLine();
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] splitString = line.split(";");
                int productId = Integer.parseInt(splitString[0]);
                String description = splitString[1];
                double price = Double.parseDouble(splitString[2]);
                int quantityInStock = Integer.parseInt(splitString[3]);
                boolean wholesaleProduct = Boolean.parseBoolean(splitString[4]);
                Product product = new Product(productId, description, price, quantityInStock, wholesaleProduct);
                products.put(productId, product);
            }
        }
        return products;
    }

    static HashMap<Integer, Product> getProducts() {
        return products;
    }
}

package main.java.ru.clevertec.check;

class Product {
    private int productId;
    private int productQuantity;
    private String description;
    private double price;
    private int quantityInStock;
    private boolean wholesaleProduct;

    public Product(int productId, int productQuantity) {
        this.productId = productId;
        this.productQuantity = productQuantity;
    }

    public Product(int productId, String description, double price, int quantityInStock, boolean wholesaleProduct) {
        this.productId = productId;
        this.description = description;
        this.price = price;
        this.quantityInStock = quantityInStock;
        this.wholesaleProduct = wholesaleProduct;
    }

    public int getProductId() {
        return productId;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public String getDescription() {
        return description;
    }

    public double getPrice() {
        return price;
    }

    public int getQuantityInStock() {
        return quantityInStock;
    }

    public boolean isWholesaleProduct() {
        return wholesaleProduct;
    }
}

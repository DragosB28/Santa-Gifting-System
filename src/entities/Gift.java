package entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import enums.Category;

public final class Gift {
    private String productName;
    private double price;
    private Category category;
    @JsonIgnore
    private int quantity;

    public Gift(final String productName, final double price,
                final Category category, final int quantity) {
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(final String productName) {
        this.productName = productName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(final double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(final Category category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(final int quantity) {
        this.quantity = quantity;
    }

    /**
     * Whenever a gift is assigned, this method is responsible to decrement
     * the quantity of that gift.
     */
    public void decrementQuantity() {
        this.quantity -= 1;
    }

    /**
     * Whenever an already existing gift is received, it's quantity
     * is increased
     * @param newQuantity the new amount to be added
     */
    public void increaseQuantity(final int newQuantity) {
        this.quantity += newQuantity;
    }

    @Override
    public String toString() {
        return "Gift{"
                +
                "productName='" + productName
                + '\''
                +
                ", price=" + price
                +
                ", category=" + category
                +
                ", quantity=" + quantity
                +
                '}';
    }
}

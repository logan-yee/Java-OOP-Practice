package main.modules;

public class Item {
    private String name;
    private double price;
    private int quantity;

    //Constructor
    public Item (String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }

    //Getters
    public double getPrice() {
        return price;
    }

    public int getQuantity() {
        return quantity;
    }

    public String getName() {
        return name;
    }
}

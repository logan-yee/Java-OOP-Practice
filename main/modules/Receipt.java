package main.modules;

import main.payment.Cash;
import main.payment.Payment;

import java.util.*;
import java.math.*;

public class Receipt {
    private double totalAmount;
    private Store store;
    private Customer customer;
    private Payment payment;
    private List<Item> items = new ArrayList<>();

    public static double taxRate = 1.13;

    //Getters
    public double getTotalAmount() {
        return totalAmount;
    }

    public List<Item> getItems() {
        return items;
    }

    public Payment getPayment() {
        return payment;
    }

    //Setters
    public void setStore(Store store) {
        this.store = store;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }

    public void addItem(Item item) {
        items.add(item);
    }

    public void calculateTotal() {
        totalAmount = 0;
        for (Item item : items) {
            totalAmount += item.getPrice() * item.getQuantity();
        }
    }

    //Methods
    public double calculateTax() {
        BigDecimal bd = new BigDecimal(this.getTotalAmount() * taxRate).setScale(2, RoundingMode.HALF_UP);

        return bd.doubleValue();
    }

    public void printReceipt() {
        System.out.println("Receipt {" + "\n" +
                "Store: " + store.getName() + "\n" +
                "Customer: " + customer.getName() + "\n");

        //Formatting to clean up receipt display
        for (Item item : items) {
            System.out.printf("%-8s $%-5.2f %-5d%n", item.getName(), item.getPrice(), item.getQuantity());
        }

        System.out.println(
                "\n" + "Total Amount: $" + getTotalAmount() + "\n" +
                        "Tax Included: $" + calculateTax() + "\n" +
                        "Payment Method: " + payment.getPaymentMethod() + "\n"
        );

        //Display change if payment is cash
        if (payment instanceof Cash) {
            double change = payment.calculateChange(calculateTax());
            System.out.println("Change returned: $" + change);
        }

        System.out.println("}");
    }
}

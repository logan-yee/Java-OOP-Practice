import java.util.*;

public class Receipt {
    private double totalAmount;
    private Store store;
    private Customer customer;
    private Payment payment;
    private List<Item> items = new ArrayList<>();

    public final double taxRate = 0.13;

    //Setters and getters
    public double getTotalAmount() {
        return totalAmount;
    }

    //Methods
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

    @Override
    public String toString() {
        return "Receipt {" + "\n" +
                "Store: " + store.getName() + "\n" +
                "Customer: " + customer.getName() + "\n" +
                "Items: " + items + "\n" +
                "Total Amount: " + getTotalAmount() + "\n" +
                "After Tax: " + (getTotalAmount() * taxRate) + "\n" +
                "}";
    }
}

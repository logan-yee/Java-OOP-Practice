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

    public void printReceipt() {
        System.out.println("Receipt {" + "\n" +
                "Store: " + store.getName() + "\n" +
                "Customer: " + customer.getName() + "\n");
        for (Item item : items) {
            System.out.printf("%-10s $%-10.2f%n %-10d", item.getName(), item.getPrice(), item.getQuantity());
        }
        System.out.println(
                "\n" + "Total Amount: " + getTotalAmount() + "\n" +
                        "Tax Included: " + (getTotalAmount() * taxRate) + "\n" +
                        "}"
        );
    }
}

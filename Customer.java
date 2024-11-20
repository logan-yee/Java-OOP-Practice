import java.util.*;

public class Customer {
    private String name;
    private List<Receipt> receipts = new ArrayList<>();

    //Constructor
    public Customer(String name) {
        this.name = name;
    }

    //Getter
    public String getName() {
        return name;
    }

    //Add receipt to customer
    public void addReceipt(Receipt receipt) {
        receipts.add(receipt);
    }

    //Display receipts
    public void viewReceipts() {
        if (receipts.isEmpty()) {
            System.out.println("No receipts for customer to display");
        } else {
            System.out.println("Receipts for " + name);
            for (Receipt receipt : receipts) {
                receipt.printReceipt();
            }
        }
    }
}

import java.util.*;

public class Store {
    private String name;
    private List<Receipt> receipts = new ArrayList<>();

    //Constructor
    public Store(String name) {
        this.name = name;
    }

    //Getter
    public String getName() {
        return name;
    }

    //Add a receipt to the store
    public void addReceipt (Receipt receipt) {
        receipts.add(receipt);
    }

    //Display receipts
    public void viewReceipts() {
        if (receipts.isEmpty()) {
            System.out.println("No receipts available");
        } else {
            System.out.println("Receipts for " + name);
            for (Receipt receipt : receipts) {
                System.out.print(receipt);
            }
        }
    }
}

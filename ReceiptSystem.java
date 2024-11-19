import java.util.ArrayList;
import java.util.Scanner;

public class ReceiptSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Create a list to store receipts
        ArrayList<Receipt> receipts = new ArrayList<>();

        // Create a list to store customers
        ArrayList<Customer> customers = new ArrayList<>();

        // Create a list to store stores
        ArrayList<Store> stores = new ArrayList<>();

        // Main loop to interact with the user
        while (true) {
            System.out.println("1. Add Receipt");
            System.out.println("2. View Receipts");
            System.out.println("3. Generate Reports");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    // Add a receipt
                    Receipt receipt = new Receipt();
                    System.out.println("Enter store name: ");
                    String storeName = scanner.next();
                    
                    //check if the store is already exists before add it
                    Store store = findStoreByName(stores, storeName);
                    if (store == null) {
                        store = new Store(storeName);
                        stores.add(store);
                    }
                    receipt.setStore(store);

                    System.out.println("Enter customer name: ");
                    String customerName = scanner.next();

                    //check if the customer is already exists before add it
                    Customer customer = findCustomerByName(customers, customerName);
                    if (customer == null) {
                        customer = new Customer(customerName);
                        customers.add(customer);
                    }
                    receipt.setCustomer(customer);

                    // Add items to the receipt
                    while (true) {
                        System.out.println("Enter item name (or 'done' to finish): ");
                        String itemName = scanner.next();
                        if (itemName.equals("done")) {
                            break;
                        }
                        System.out.println("Enter item price: ");
                        double price = scanner.nextDouble();
                        System.out.println("Enter item quantity: ");
                        int quantity = scanner.nextInt();
                        Item item = new Item(itemName, price, quantity);
                        receipt.addItem(item);
                    }

                    // Calculate total and add receipt to lists
                    receipt.calculateTotal();
                    receipts.add(receipt);
                    store.addReceipt(receipt);
                    customer.addReceipt(receipt);

                    break;

                case 2:
                    // View receipts
                    System.out.println("View receipts by:");
                    System.out.println("1. Customer");
                    System.out.println("2. Store");
                    System.out.print("Enter your choice: ");
                    int viewChoice = scanner.nextInt();

                    if (viewChoice == 1) {
                        System.out.print("Enter customer name: ");
                        String customerNameToView = scanner.next();
                        Customer customerToView = findCustomerByName(customers, customerNameToView);
                        if (customerToView != null) {
                            customerToView.viewReceipts();
                        } else {
                            System.out.println("Customer not found.");
                        }
                    } else if (viewChoice == 2) {
                        System.out.print("Enter store name: ");
                        String storeNameToView = scanner.next();
                        Store storeToView = findStoreByName(stores, storeNameToView);
                        if (storeToView != null) {
                            storeToView.viewReceipts();
                        } else {
                            System.out.println("Store not found.");
                        }
                    }

                    break;

                case 3:
                    // Generate reports
                    // Implement report generation methods here
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting...");
                    System.exit(0);
            }
        }
    }

    // Helper methods
    private static Customer findCustomerByName(ArrayList<Customer> customers, String name) {
            // Implement the customrt search method
        return null;
    }

    private static Store findStoreByName(ArrayList<Store> stores, String name) {
            // Implement the store search method
        return null;
    }
}
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
            String choice = scanner.next();

            //Clean input with error handling utility class
            int cleanChoice = InputCleaner.cleanToIntegers(choice);

            switch (cleanChoice) {
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

                    //check if the customer already exists before add it
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
                        if (itemName.equalsIgnoreCase("done")) {
                            break;
                        }
                        System.out.println("Enter item price: ");
                        String priceInput = scanner.next();

                        //Error handling
                        double price = InputCleaner.cleanToDouble(priceInput);

                        System.out.println("Enter item quantity: ");
                        String itemInput = scanner.next();

                        //Error handling
                        int quantity = InputCleaner.cleanToIntegers(itemInput);

                        Item item = new Item(itemName, price, quantity);
                        receipt.addItem(item);
                    }

                    // Calculate total
                    receipt.calculateTotal();

                    System.out.println("Payment Type ");
                    System.out.println("1. Cash");
                    System.out.println("2. Credit");
                    System.out.println("3. Debit");
                    System.out.print("Enter your choice: ");
                    String paymentChoice = scanner.next();

                    //Error handling
                    int cleanPaymentChoice = InputCleaner.cleanToIntegers(paymentChoice);

                    switch(cleanPaymentChoice) {
                        case 1:
                            //Cash
                            System.out.println("Total after tax: " + receipt.calculateTax());
                            System.out.print("Enter amount paid in cash: $");
                            double cashPaid = scanner.nextDouble();

                            Payment payment = new Cash(cashPaid);
                            receipt.setPayment(payment);
                            break;
                        case 2:
                            //Credit
                            System.out.println("Total after tax: " + receipt.calculateTax());
                            System.out.print("Enter amount paid in credit: $");
                            double creditPaid = scanner.nextDouble();

                            if (creditPaid != receipt.calculateTax()) {
                                System.out.print("Error: Credit payments must match the total amount exactly." + "\n");
                                break;
                            }

                            Payment creditPayment = new Credit(creditPaid);
                            receipt.setPayment(creditPayment);
                            break;
                        case 3:
                            // Debit payment
                            System.out.println("Total after tax: " + receipt.calculateTax());
                            System.out.print("Enter amount to pay with debit: $");
                            double debitPaid = scanner.nextDouble();

                            if (debitPaid != receipt.calculateTax()) {
                                System.out.println("Error: Debit payments must match the total amount exactly." + "\n");
                                break;
                            }

                            Payment debitPayment = new Debit(debitPaid);
                            receipt.setPayment(debitPayment);
                            break;

                        default:
                            System.out.println("Invalid payment choice. Please try again.");
                    }

                    //Add receipt to list
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
                    String viewChoice = scanner.next();

                    //Error handling
                    int cleanViewChoice = InputCleaner.cleanToIntegers(viewChoice);

                    if (cleanViewChoice == 1) {
                        System.out.print("Enter customer name: ");
                        String customerNameToView = scanner.next();
                        Customer customerToView = findCustomerByName(customers, customerNameToView);
                        if (customerToView != null) {
                            customerToView.viewReceipts();
                        } else {
                            System.out.println("Customer not found.");
                        }
                    } else if (cleanViewChoice == 2) {
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
                    // Generate Reports

                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("An error occured, please try again");
            }
        }
    }

    // Helper methods
    private static Customer findCustomerByName(ArrayList<Customer> customers, String name) {
            for (Customer customer : customers) {
                if (customer.getName().equalsIgnoreCase(name)) {
                    return customer;
                }
            }
        return null;
    }

    private static Store findStoreByName(ArrayList<Store> stores, String name) {
            for (Store store : stores) {
                if (store.getName().equalsIgnoreCase(name)) {
                    return store;
                }
            }
        return null;
    }

}
package main;

import main.modules.Customer;
import main.modules.Item;
import main.modules.Receipt;
import main.modules.Store;
import main.payment.Cash;
import main.payment.Credit;
import main.payment.Debit;
import main.utils.InputCleaner;

import java.util.*;

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
                        Scanner itemScanner = new Scanner(System.in);
                        System.out.println("Enter item name (or 'done' to finish): ");
                        String itemName = itemScanner.nextLine();
                        if (itemName.equalsIgnoreCase("done")) {
                            break;
                        }
                        System.out.println("Enter item price: ");
                        String priceInput = itemScanner.nextLine();

                        //Error handling
                        double price = InputCleaner.cleanToDouble(priceInput);

                        System.out.println("Enter item quantity: ");
                        String itemInput = itemScanner.nextLine();

                        //Error handling
                        int quantity = InputCleaner.cleanToIntegers(itemInput);

                        Item item = new Item(itemName, price, quantity);
                        receipt.addItem(item);
                    }

                    // Calculate total
                    receipt.calculateTotal();

                    handlePayment(scanner, receipt);

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
                    System.out.println("Generate Reports:");
                    System.out.println("1. Spending by Customer");
                    System.out.println("2. Spending by Store");
                    System.out.println("3. Overall Spending Summary");
                    System.out.println("4. Item-Wise Spending");
                    System.out.println("5. Payment Method Summary");
                    System.out.print("Enter your choice: ");
                    int reportChoice = scanner.nextInt();

                    switch (reportChoice) {
                        case 1:
                            generateCustomerReport(customers);
                            break;
                        case 2:
                            generateStoreReport(stores);
                            break;
                        case 3:
                            generateOverallSummary(receipts);
                            break;
                        case 4:
                            generateItemReport(receipts);
                            break;
                        case 5:
                            generatePaymentReport(receipts);
                            break;
                        default:
                            System.out.println("Invalid choice. Returning to main menu.");
                    }
                    break;

                case 4:
                    // Exit
                    System.out.println("Exiting...");
                    System.exit(0);
                default:
                    System.out.println("An error occurred, please try again");
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

    private static void handlePayment(Scanner scanner, Receipt receipt) {
        try {
            System.out.println("Payment Type:");
            System.out.println("1. Cash");
            System.out.println("2. Credit");
            System.out.println("3. Debit");
            System.out.print("Enter your choice: ");
            int paymentChoice = InputCleaner.cleanToIntegers(scanner.next());

            double totalAfterTax = receipt.calculateTax();
            switch (paymentChoice) {
                case 1:
                    System.out.println("Total after tax: " + totalAfterTax);
                    System.out.print("Enter amount paid in cash: $");
                    double cashPaid = InputCleaner.cleanToDouble(scanner.next());
                    receipt.setPayment(new Cash(cashPaid));
                    System.out.println("Receipt Added Successfully!");
                    break;

                case 2:
                    System.out.println("Total after tax: " + totalAfterTax);
                    System.out.print("Enter amount paid with credit: $");
                    double creditPaid = InputCleaner.cleanToDouble(scanner.next());

                    if (creditPaid != totalAfterTax) throw new IllegalArgumentException("Credit payments must match the total amount.");

                    receipt.setPayment(new Credit(creditPaid));
                    System.out.println("Receipt Added Successfully!");
                    break;

                case 3:
                    System.out.println("Total after tax: " + totalAfterTax);
                    System.out.print("Enter amount paid with debit: $");
                    double debitPaid = InputCleaner.cleanToDouble(scanner.next());

                    if (debitPaid != totalAfterTax) throw new IllegalArgumentException("Debit payments must match the total amount.");

                    receipt.setPayment(new Debit(debitPaid));
                    System.out.println("Receipt Added Successfully!");
                    break;

                default:
                    System.out.println("Invalid payment choice.");
            }
        } catch (Exception e) {
            System.out.println("Error during payment processing: " + e.getMessage());
        }
    }

    //Report generation
    private static void generateStoreReport(ArrayList<Store> stores) {
        System.out.println("Store Spending Report:");
        for (Store store : stores) {
            double totalSpent = 0;
            System.out.println("Store: " + store.getName());
            for (Receipt receipt : store.getReceipts()) {
                System.out.println(receipt); // Print each receipt
                totalSpent += receipt.getTotalAmount();
            }
            System.out.println("Total Spending at " + store.getName() + ": $" + totalSpent);
            System.out.printf("Total Spending after Tax: $%.2f \n", (totalSpent * Receipt.taxRate));
        }
    }

    private static void generateCustomerReport(ArrayList<Customer> customers) {
        System.out.println("Customer Spending Report:");
        for (Customer customer : customers) {
            double totalSpent = 0;
            System.out.println("Customer: " + customer.getName());
            for (Receipt receipt : customer.getReceipts()) {
                System.out.println(receipt); // Print each receipt
                totalSpent += receipt.getTotalAmount();
            }
            System.out.println("Total Spending by " + customer.getName() + ": $" + totalSpent);
            System.out.printf("Total Spending after Tax: $%.2f \n", (totalSpent * Receipt.taxRate));
        }
    }

    private static void generateOverallSummary(ArrayList<Receipt> receipts) {
        double totalSpending = 0;
        System.out.println("Overall Spending Summary:");
        for (Receipt receipt : receipts) {
            System.out.println(receipt); // Print each receipt
            totalSpending += receipt.getTotalAmount();
        }
        System.out.println("Total Spending Across All Receipts: $" + totalSpending);
        System.out.printf("Total Spending after Tax: $%.2f \n", (totalSpending * Receipt.taxRate));
    }

    private static void generateItemReport(ArrayList<Receipt> receipts) {
        HashMap<String, Double> itemSpending = new HashMap<>();

        for (Receipt receipt : receipts) {
            for (Item item : receipt.getItems()) {
                double totalForItem = item.getPrice() * item.getQuantity();
                itemSpending.put(item.getName(), itemSpending.getOrDefault(item.getName(), 0.0) + totalForItem);
            }
        }

        System.out.println("Item-Wise Spending:");
        for (Map.Entry<String, Double> entry : itemSpending.entrySet()) {
            System.out.println("Item: " + entry.getKey() + ", Total Spending: $" + (entry.getValue() * Receipt.taxRate) + "\n");
        }
    }

    private static void generatePaymentReport(ArrayList<Receipt> receipts) {
        HashMap<String, Double> paymentSpending = new HashMap<>();

        for (Receipt receipt : receipts) {
            String paymentMethod = receipt.getPayment().getPaymentMethod();
            paymentSpending.put(paymentMethod, paymentSpending.getOrDefault(paymentMethod, 0.0) + receipt.getTotalAmount());
        }

        System.out.println("Payment Method Summary:");
        for (Map.Entry<String, Double> entry : paymentSpending.entrySet()) {
            System.out.println("Payment Method: " + entry.getKey() + ", Total Spending: $" + (entry.getValue() * Receipt.taxRate) + "\n");
        }
    }

}
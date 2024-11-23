# **main.modules.Receipt Management System**

The main.modules.Receipt Management System is a Java-based console application designed to manage receipts for customers and stores. It allows users to add receipts, view receipts, generate various reports, and handle multiple payment methods. The application follows Object-Oriented Programming principles and incorporates error handling for a robust user experience.

---

## **Features**

1. **Add main.modules.Receipt**:
   - Add receipts for specific stores and customers.
   - Add multiple items to a receipt.
   - Calculate the total amount, including tax.
   - Choose and validate payment methods:
     - **main.payment.Cash**: Supports overpayments and calculates change.
     - **main.payment.Credit/main.payment.Debit**: Requires exact payments.

2. **View Receipts**:
   - View receipts by **customer** or **store**.

3. **Generate Reports**:
   - Spending by customer.
   - Spending by store.
   - Overall spending summary.
   - main.modules.Item-wise spending breakdown.
   - Spending summary by payment method.

4. **Error Handling**:
   - Input validation for numeric fields (e.g., prices, quantities, payment amounts).
   - Graceful handling of invalid inputs with informative error messages.
   - Prevents invalid operations (e.g., underpayments for credit/debit).

---

## **System Requirements**

- **Java Version**: Java SE 8 or later
- **Development Environment**: Any Java IDE (e.g., IntelliJ IDEA, Eclipse, VS Code)

---

## **How to Run the Application**

1. Clone or download the project files.
2. Open the project in your preferred Java IDE.
3. Compile and run the `main.ReceiptSystem` class.
4. Follow the console prompts to interact with the system.

---

## **Application Workflow**

1. **Main Menu**:
   - `1. Add main.modules.Receipt`: Add a new receipt to the system.
   - `2. View Receipts`: View receipts by customer or store.
   - `3. Generate Reports`: Generate various spending reports.
   - `4. Exit`: Exit the application.

2. **Adding a main.modules.Receipt**:
   - Enter store and customer names.
   - Add multiple items (name, price, quantity).
   - Choose a payment method:
     - **main.payment.Cash**: Enter the amount paid. If overpaid, change is calculated.
     - **main.payment.Credit/main.payment.Debit**: The payment must match the total amount after tax.

3. **Viewing Receipts**:
   - View receipts by customer or store name.

4. **Generating Reports**:
   - Choose from various report types to analyze spending patterns.

---

## **Code Structure**

### **1. Classes**

#### **Main Class**
- `main.ReceiptSystem`: The entry point of the application, handling user interaction and menu navigation.

#### **Core Classes**
- `main.modules.Receipt`: Represents a receipt, including store, customer, items, and payment.
- `main.modules.Item`: Represents an item on a receipt (name, price, quantity).
- `main.modules.Store`: Represents a store, holding associated receipts.
- `main.modules.Customer`: Represents a customer, holding associated receipts.

#### **main.payment.Payment Classes**
- `main.payment.Payment` (Interface): Defines the payment method structure.
- `main.payment.Cash`: Handles cash payments, including change calculation.
- `main.payment.Credit`: Handles credit payments with strict validation.
- `main.payment.Debit`: Handles debit payments with strict validation.

#### **Utility Classes**
- `main.utils.InputCleaner`: Provides methods to validate and clean user inputs (e.g., integers, doubles).

---

### **2. Key Methods**

- **`addReceipt(Scanner scanner, ArrayList<main.modules.Receipt> receipts, ArrayList<main.modules.Customer> customers, ArrayList<main.modules.Store> stores)`**:
  Handles adding receipts, validating inputs, and setting payment methods.

- **`viewReceipts(Scanner scanner, ArrayList<main.modules.Customer> customers, ArrayList<main.modules.Store> stores)`**:
  Allows viewing receipts by customer or store.

- **`generateReports(Scanner scanner, ArrayList<main.modules.Receipt> receipts, ArrayList<main.modules.Customer> customers, ArrayList<main.modules.Store> stores)`**:
  Generates detailed reports on spending patterns.

- **`handlePayment(Scanner scanner, main.modules.Receipt receipt)`**:
  Handles payment type selection and validation.

---

## **Error Handling**

- **Invalid Menu Choices**:
  - Displays appropriate messages for invalid menu inputs and prompts for re-entry.

- **Numeric Input Validation**:
  - Ensures valid numbers for item prices, quantities, and payment amounts.

- **main.payment.Payment Validation**:
  - Enforces exact payments for credit and debit methods.
  - Handles overpayment for cash with accurate change calculation.

- **Graceful Failure**:
  - Catches unexpected exceptions and provides user-friendly error messages.

---

## **Example Interactions**

### **1. Adding a main.modules.Receipt**
```
1. Add main.modules.Receipt
Enter store name: Walmart
Enter customer name: John Doe
Enter item name (or 'done' to finish): Milk
Enter item price: 4.50
Enter item quantity: 2
Enter item name (or 'done' to finish): Bread
Enter item price: 2.00
Enter item quantity: 1
Enter item name (or 'done' to finish): done
main.payment.Payment Type:
1. main.payment.Cash
2. main.payment.Credit
3. main.payment.Debit
Enter your choice: 1
Total after tax: 8.89
Enter amount paid in cash: 10.00
main.modules.Receipt added successfully!
```

### **2. Viewing Receipts**
```
2. View Receipts
View receipts by:
1. main.modules.Customer
2. main.modules.Store
Enter your choice: 1
Enter customer name: John Doe
main.modules.Receipt {
  main.modules.Store: Walmart
  main.modules.Customer: John Doe
  Items:
    Milk $4.50 2
    Bread $2.00 1
  Total: $8.89
  main.payment.Payment Method: main.payment.Cash
  Change returned: $1.11
}
```

---

## **Potential Enhancements**

1. **Database Integration**:
   - main.modules.Store and retrieve data using a relational database like MySQL.
2. **GUI**:
   - Develop a graphical user interface for enhanced usability.
3. **Export Reports**:
   - Allow exporting reports to CSV or PDF formats.
4. **Advanced Filtering**:
   - Add filters for viewing and generating specific receipts or reports.

---

Enjoy using the **main.modules.Receipt Management System**!

package main.payment;

public class Credit implements Payment {
    private double amountPaid;

    //Constructor
    public Credit(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentMethod() {
        return "Credit";
    }

    @Override
    public double calculateChange(double totalAmountAfterTax) {
        return 0;
    }
}

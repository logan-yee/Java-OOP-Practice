public class Debit implements Payment{
    private double amountPaid;

    //Constructor
    public Debit(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentMethod() {
        return "Debit";
    }

    @Override
    public double calculateChange(double totalAmountAfterTax) {
        return 0;
    }
}

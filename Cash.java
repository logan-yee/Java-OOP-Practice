public class Cash implements Payment{
    private double cashGiven;

    //Constructor
    public Cash(double cashGiven) {
        this.cashGiven = cashGiven;
    }

    //Getter
    public String getPaymentMethod() {
        return "Cash";
    }

    public double calculateChange(double totalAmount) {

         double change = cashGiven - (totalAmount * Receipt.taxRate);

         return Math.round(change / 0.05) * 0.05;
    }
}

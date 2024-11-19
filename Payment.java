public class Payment {
    private String paymentType;
    private double paymentAmount;

    //Getters
    public String getPaymentType() {
        return paymentType;
    }
    public double getPaymentAmount() {
        return paymentAmount;
    }

    //Setters
    public void setPaymentType(String paymentType) {
       this.paymentType = paymentType;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }

}

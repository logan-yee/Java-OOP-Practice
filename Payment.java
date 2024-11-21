public interface Payment {

    String getPaymentMethod();

    double calculateChange(double totalAmount);
}

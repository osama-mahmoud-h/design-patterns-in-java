package structural.adapter;

// Simulate a third-party PayPal library with its own specific API
public class PayPalService {
    public void makePayment(double amount) {
        System.out.println("Paying $" + amount + " using PayPal.");
    }
}


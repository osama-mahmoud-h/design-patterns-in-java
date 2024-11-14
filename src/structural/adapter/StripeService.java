package structural.adapter;

// Simulate a third-party Stripe library with its own specific API
public class StripeService {
    public void createCharge(double amount) {
        System.out.println("Charging $" + amount + " using Stripe.");
    }
}

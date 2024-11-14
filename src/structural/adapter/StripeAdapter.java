package structural.adapter;

public class StripeAdapter implements PaymentProcessor {
    private final StripeService stripeService;

    public StripeAdapter(StripeService stripeService) {
        this.stripeService = stripeService;
    }

    @Override
    public void processPayment(double amount) {
        stripeService.createCharge(amount);
    }
}

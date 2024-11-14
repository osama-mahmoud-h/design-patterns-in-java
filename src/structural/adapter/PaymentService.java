package structural.adapter;

public class PaymentService {
    private final PaymentProcessor paymentProcessor;

    // Constructor accepts any implementation of PaymentProcessor
    public PaymentService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void makePayment(double amount) {
        paymentProcessor.processPayment(amount);
    }

    public static void main(String[] args) {
        // Using Stripe for payment
        StripeService stripeService = new StripeService();
        PaymentProcessor stripeAdapter = new StripeAdapter(stripeService);
        PaymentService stripePaymentService = new PaymentService(stripeAdapter);
        stripePaymentService.makePayment(100.00); // Output: Charging $100.0 using Stripe.

        // Using PayPal for payment
        PayPalService payPalService = new PayPalService();
        PaymentProcessor payPalAdapter = new PayPalAdapter(payPalService);
        PaymentService payPalPaymentService = new PaymentService(payPalAdapter);
        payPalPaymentService.makePayment(200.00); // Output: Paying $200.0 using PayPal.
    }
}


package structural.adapter;

public class PayPalAdapter implements PaymentProcessor {
    private final PayPalService payPalService;

    public PayPalAdapter(PayPalService payPalService) {
        this.payPalService = payPalService;
    }

    @Override
    public void processPayment(double amount) {
        payPalService.makePayment(amount);
    }
}


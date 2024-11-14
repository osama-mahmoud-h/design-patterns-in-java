
# Adapter Design Pattern

## Problem Definition

The **Adapter** design pattern is a **structural pattern** that allows two incompatible interfaces to work together. It acts as a bridge between two objects, allowing classes with incompatible interfaces to interact seamlessly.

### Problems Before Adapter Pattern

Imagine you’re building a system that needs to integrate third-party libraries or legacy code, but the interfaces of these external components are incompatible with your current code. For example, consider an e-commerce application that needs to process payments through various third-party payment gateways like Stripe and PayPal, each of which has its own interface.

#### Problems Before Adapter Pattern

1. **Interface Incompatibility**: Integrating incompatible interfaces clutters code with conversion logic.
2. **Code Duplication**: Without a clear solution, you may need to rewrite or duplicate code for compatibility.
3. **Limited Flexibility**: Modifying the client code every time there’s a change in an external interface becomes challenging.

---

## Solution: Adapter Pattern

The **Adapter pattern** provides a way to make incompatible interfaces compatible. By creating an `Adapter` class, you can wrap the incompatible interface and convert it into a form that’s usable by your code. This allows the client to interact with the target interface as if it were the original, without knowing the details of the adapter.

### How the Adapter Pattern Works

1. **Target Interface**: The interface that the client expects to work with.
2. **Adapter Class**: Implements the target interface and wraps an instance of the incompatible interface, converting requests into a form compatible with the adaptee.
3. **Adaptee**: The incompatible interface that needs to be adapted to the target interface.
4. **Client**: Uses the target interface without needing to know about the adapter’s inner workings.

---

## Real-Life Example: Payment Gateway Integration in Java

Let’s look at an example where the Adapter pattern is used to integrate two payment processors, Stripe and PayPal, into an e-commerce application.

### Step 1: Define the Target Interface (`PaymentProcessor`)

The application interacts only with the `PaymentProcessor` interface, allowing flexibility to use any payment gateway that adheres to this standard.

```java
// Target Interface
public interface PaymentProcessor {
    void processPayment(double amount);
}
```

### Step 2: Define Adaptees (Third-Party Libraries)

#### StripeService (Adaptee)

```java
// Simulate a third-party Stripe library with its own specific API
public class StripeService {
    public void createCharge(double amount) {
        System.out.println("Charging $" + amount + " using Stripe.");
    }
}
```

#### PayPalService (Adaptee)

```java
// Simulate a third-party PayPal library with its own specific API
public class PayPalService {
    public void makePayment(double amount) {
        System.out.println("Paying $" + amount + " using PayPal.");
    }
}
```

### Step 3: Create Adapter Classes for Each Payment Service

Each adapter will implement the `PaymentProcessor` interface and translate the `processPayment` method to the specific method used by the third-party library.

#### StripeAdapter

```java
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
```

#### PayPalAdapter

```java
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
```

### Step 4: Implement Client Code to Use the Adapters

The client code can decide which payment method to use and work with a consistent interface (`PaymentProcessor`) without worrying about the underlying library.

```java
public class PaymentService {
    private final PaymentProcessor paymentProcessor;

    public PaymentService(PaymentProcessor paymentProcessor) {
        this.paymentProcessor = paymentProcessor;
    }

    public void makePayment(double amount) {
        paymentProcessor.processPayment(amount);
    }
}
```

### Step 5: Test the Payment Service with Different Adapters

```java
public class Main {
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
```

---

## Explanation of How Adapter Solves the Problem

1. **Unified Interface**: The `PaymentProcessor` interface provides a single method `processPayment` for handling payments.
2. **Encapsulation of Incompatible APIs**: Each adapter encapsulates the specific payment gateway API details.
3. **Flexible Integration**: New payment gateways can be added by simply creating a new adapter.

---

## Benefits of the Adapter Pattern

- **Loose Coupling**: The application is loosely coupled to the payment gateways, allowing easy changes or additions of new gateways.
- **Code Reusability**: The `PaymentProcessor` interface and service code don’t need to change with each new gateway integration.
- **Easier Testing and Mocking**: Mock implementations of `PaymentProcessor` can be easily injected for unit testing.

## Drawbacks of the Adapter Pattern

- **Increased Complexity**: Adding adapters can increase the code complexity, especially if many adapters are required.
- **Adapter Overuse**: Relying heavily on adapters can lead to excessive wrappers around objects.

---

## Summary

The Adapter pattern provides a way to work with incompatible interfaces by creating a bridge between the client and the adaptee. It’s useful for integrating external services like payment gateways, allowing the system to work with various services in a loosely coupled, flexible, and extensible way.

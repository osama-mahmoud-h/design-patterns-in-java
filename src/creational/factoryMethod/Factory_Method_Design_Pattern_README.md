
# Factory Method Design Pattern

## What is the Factory Method Pattern?

The **Factory Method** design pattern is a **creational pattern** used to define an interface or abstract class for creating objects, but it allows subclasses to alter the type of object that will be created. This pattern promotes loose coupling by removing the need for clients to know the specific classes that will be instantiated.

### When to Use the Factory Method Pattern

1. **When Object Creation is Complex**: If creating an object involves several steps or specific configurations, the Factory Method can manage this complexity.
2. **When You Need a Family of Related Products**: If you want different subclasses to create different types of products but still share a common interface.
3. **When You Want to Avoid Tight Coupling**: By using an interface or abstract class, clients are not dependent on specific classes for object creation.

### Key Concepts

- **Product**: Defines the interface or abstract class for objects that will be created.
- **Concrete Product**: Implements the `Product` interface, representing the actual objects created by the factory.
- **Creator**: Declares the factory method that returns a `Product` object. This may be abstract or provide a default implementation.
- **Concrete Creator**: Overrides the factory method to return an instance of a `ConcreteProduct`.

---

## Problem Without Factory Method

Before using the Factory Method pattern, code for object creation might lead to:

1. **Tight Coupling Between Classes**: Client classes directly depend on specific classes, making it harder to extend the system with new types.
2. **Code Duplication**: If multiple places in the code need to create similar objects, the creation logic is duplicated.
3. **Lack of Flexibility**: Adding new types requires modifying multiple parts of the codebase.

### Example Without Factory Method

Consider a Transport system that creates different vehicle objects (Car, Bike) directly in the client code:

```java
// Vehicle Interface
interface Vehicle {
    void deliver();
}

// Concrete Vehicle Classes
class Car implements Vehicle {
    @Override
    public void deliver() {
        System.out.println("Delivering by car.");
    }
}

class Bike implements Vehicle {
    @Override
    public void deliver() {
        System.out.println("Delivering by bike.");
    }
}

// Client Class: TransportService
public class TransportService {
    private Vehicle vehicle;

    public TransportService(String vehicleType) {
        if (vehicleType.equals("car")) {
            vehicle = new Car();
        } else if (vehicleType.equals("bike")) {
            vehicle = new Bike();
        } else {
            throw new IllegalArgumentException("Unknown vehicle type");
        }
    }

    public void startDelivery() {
        vehicle.deliver();
    }

    public static void main(String[] args) {
        TransportService carService = new TransportService("car");
        carService.startDelivery();  // Output: Delivering by car.

        TransportService bikeService = new TransportService("bike");
        bikeService.startDelivery();  // Output: Delivering by bike.
    }
}
```

---

## Solution Using Factory Method Pattern

The Factory Method pattern allows us to encapsulate the creation of `Vehicle` objects and solve issues with tight coupling, duplication, and inflexibility.

1. Define an `AnimalFactory` interface with a factory method that subclasses implement to create specific vehicles.
2. Each vehicle type (e.g., `CarFactory`, `BikeFactory`) creates its respective vehicle.
3. `TransportService` interacts with `VehicleFactory` and doesn’t need to know the specific vehicle type.

### Step-by-Step Solution Using Factory Method

#### 1. Define the `Vehicle` Interface

```java
interface Vehicle {
    void deliver();
}
```

#### 2. Create `ConcreteVehicle` Classes

```java
class Car implements Vehicle {
    @Override
    public void deliver() {
        System.out.println("Delivering by car.");
    }
}

class Bike implements Vehicle {
    @Override
    public void deliver() {
        System.out.println("Delivering by bike.");
    }
}
```

#### 3. Define the `VehicleFactory` Abstract Class

```java
abstract class VehicleFactory {
    public abstract Vehicle createVehicle();  // Factory method
}
```

#### 4. Create Concrete Factories for Each Vehicle Type

```java
class CarFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Car();
    }
}

class BikeFactory extends VehicleFactory {
    @Override
    public Vehicle createVehicle() {
        return new Bike();
    }
}
```

#### 5. Use `TransportService` as the Client of `VehicleFactory`

```java
public class TransportService {
    private Vehicle vehicle;

    public TransportService(VehicleFactory factory) {
        this.vehicle = factory.createVehicle();
    }

    public void startDelivery() {
        vehicle.deliver();
    }

    public static void main(String[] args) {
        VehicleFactory carFactory = new CarFactory();
        TransportService carService = new TransportService(carFactory);
        carService.startDelivery();  // Output: Delivering by car.

        VehicleFactory bikeFactory = new BikeFactory();
        TransportService bikeService = new TransportService(bikeFactory);
        bikeService.startDelivery();  // Output: Delivering by bike.
    }
}
```

---

## Benefits of the Factory Method in This Example

1. **Decoupling of Classes**: `TransportService` no longer knows about specific vehicle types (`Car`, `Bike`), only `VehicleFactory`.
2. **Avoiding Code Duplication**: The creation logic for each type of vehicle is encapsulated within its factory.
3. **Improved Flexibility and Extensibility**: New vehicle types can be added easily by creating new concrete factories.
4. **Centralized Control of Object Creation**: Each factory is responsible for creating its respective product, making the client code simpler.

---

## Summary

The Factory Method pattern provides a flexible, extensible solution to the problems of tight coupling, code duplication, and lack of flexibility. By delegating the responsibility of object creation to subclasses (factories), it keeps the client code clean, modular, and easy to extend with new types.

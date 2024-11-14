
# Builder Design Pattern

## Problem Definition

The **Builder** design pattern is a **creational pattern** used to construct complex objects step by step. Unlike other creational patterns, Builder doesn’t require the object to be fully constructed in a single call, making it ideal for creating complex objects with multiple configurations or optional properties.

### Problems Before Builder Pattern

Imagine you’re designing a system where you need to create objects with numerous optional or complex attributes, like a `House` object. A `House` can have many attributes such as:

- Foundation type
- Wall material
- Number of floors
- Roof type
- Interior decorations

Creating a `House` with every possible combination of these attributes could lead to:

1. **Telescoping Constructors**: Multiple constructors for every combination of attributes.
2. **Readability Issues**: Long parameter lists make code hard to read and maintain.
3. **Immutability Challenges**: Constructing immutable objects with optional parameters becomes complex.

---

## Solution: Builder Pattern

The **Builder pattern** solves these issues by providing a way to construct an object incrementally. Each optional attribute is set individually, and the final object is constructed with a `build()` method. This pattern separates the construction of an object from its representation, making it easy to produce complex objects step-by-step.

### How the Builder Pattern Works

1. **Product**: The complex object to be built, such as a `House`.
2. **Builder Interface**: Declares methods for setting individual parts of the product.
3. **Concrete Builder**: Implements the Builder interface, providing specific implementations for each part of the product.
4. **Director (Optional)**: Manages the building process using a builder, deciding the sequence of steps to create a product.

The client uses a `Builder` object to configure each attribute and then calls `build()` to retrieve the final product.

---

## Example: Builder Pattern in Java

Suppose we want to create a `House` object using the Builder pattern.

### Step 1: Define the Product Class (`House`)

```java
public class House {
    private String foundation;
    private String walls;
    private String roof;
    private int numberOfRooms;
    private boolean hasGarage;
    private boolean hasSwimmingPool;

    private House(HouseBuilder builder) {
        this.foundation = builder.foundation;
        this.walls = builder.walls;
        this.roof = builder.roof;
        this.numberOfRooms = builder.numberOfRooms;
        this.hasGarage = builder.hasGarage;
        this.hasSwimmingPool = builder.hasSwimmingPool;
    }

    @Override
    public String toString() {
        return "House with " + foundation + " foundation, " + walls + " walls, " +
                roof + " roof, " + numberOfRooms + " rooms, " +
                (hasGarage ? "a garage" : "no garage") + ", " +
                (hasSwimmingPool ? "a swimming pool" : "no swimming pool");
    }

    public static class HouseBuilder {
        private String foundation;
        private String walls;
        private String roof;
        private int numberOfRooms;
        private boolean hasGarage;
        private boolean hasSwimmingPool;

        public HouseBuilder setFoundation(String foundation) {
            this.foundation = foundation;
            return this;
        }

        public HouseBuilder setWalls(String walls) {
            this.walls = walls;
            return this;
        }

        public HouseBuilder setRoof(String roof) {
            this.roof = roof;
            return this;
        }

        public HouseBuilder setNumberOfRooms(int numberOfRooms) {
            this.numberOfRooms = numberOfRooms;
            return this;
        }

        public HouseBuilder setGarage(boolean hasGarage) {
            this.hasGarage = hasGarage;
            return this;
        }

        public HouseBuilder setSwimmingPool(boolean hasSwimmingPool) {
            this.hasSwimmingPool = hasSwimmingPool;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }
}
```

### Step 2: Using the Builder to Create a `House` Object

```java
public class Main {
    public static void main(String[] args) {
        House house = new House.HouseBuilder()
                .setFoundation("Concrete")
                .setWalls("Brick")
                .setRoof("Shingle")
                .setNumberOfRooms(4)
                .setGarage(true)
                .setSwimmingPool(false)
                .build();

        System.out.println(house);
    }
}
```

Output:

```
House with Concrete foundation, Brick walls, Shingle roof, 4 rooms, a garage, no swimming pool
```

---

## Explanation of How Builder Solves the Problem

1. **No Telescoping Constructors**: Each attribute is set through the Builder, eliminating the need for multiple constructors.
2. **Improved Readability**: Method chaining allows attributes to be set with meaningful names, making code easier to understand.
3. **Immutable Product**: `House` can be immutable because all properties are set via the Builder.
4. **Easy to Add Optional Parameters**: Adding or removing optional attributes is straightforward by adding/removing methods in the `Builder`.

---

## Benefits of the Builder Pattern

- **Improved Readability**: Each attribute has a clear setter method, making the code more readable.
- **Flexibility and Extensibility**: New optional parameters can be added without changing client code.
- **Immutable Product**: Allows the creation of immutable objects.
- **Complex Object Creation**: Manages objects with many optional parameters or complex construction processes.

## Drawbacks of the Builder Pattern

- **Increased Complexity**: Adding a Builder class can be overkill for simple objects.
- **More Boilerplate Code**: Creating a separate Builder class with setters for each attribute adds more code.

---

## Summary

The Builder pattern provides a way to construct complex objects step-by-step, addressing issues of readability, flexibility, and immutability. It’s particularly useful when creating objects with numerous optional parameters or complex construction processes, allowing the client to build customized objects without cluttered constructors.

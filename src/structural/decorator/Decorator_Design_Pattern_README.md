
# Decorator Design Pattern

## Problem Definition

The **Decorator** design pattern is a **structural pattern** used to dynamically add new functionality to an object without altering its structure or modifying its code. This pattern allows for flexible and reusable extension of objects, enabling you to wrap objects with additional behavior in a layered fashion.

### Problems Before the Decorator Pattern

Imagine you’re building a system that processes different types of data streams. Each data stream might need various additional behaviors, such as compression, encryption, or buffering. Instead of creating multiple subclasses for every possible combination of these behaviors (e.g., `CompressedEncryptedStream`, `BufferedCompressedStream`), it would be more efficient to add functionalities dynamically.

#### Problems Before the Decorator Pattern

1. **Class Explosion**: Creating a subclass for each combination of functionality quickly becomes unmanageable.
2. **Inflexibility**: Once a class is created with specific functionality, it cannot be altered at runtime without modifying its code.
3. **Rigid Inheritance Structure**: Traditional inheritance leads to rigid structures where adding new functionality requires creating additional subclasses.

---

## Solution: Decorator Pattern

The **Decorator pattern** solves these issues by allowing you to wrap an object in a series of decorators, each adding a layer of behavior. This pattern promotes composition over inheritance, enabling you to add functionality to objects at runtime.

---

## How the Decorator Pattern Works

1. **Component Interface**: Declares operations that both concrete components and decorators implement.
2. **Concrete Component**: The base object to which new behaviors can be added.
3. **Decorator Class**: Implements the same interface as the component and holds a reference to a component object.
4. **Concrete Decorators**: Each decorator class extends the functionality of the component by wrapping the component and adding behavior.

---

## Example: Decorator Pattern in Java

Let’s use a data stream example where we have a `DataStream` interface. We’ll create decorators that add different functionalities, such as compressing and encrypting the data stream.

### Step 1: Define the Component Interface

```java
// Component Interface
public interface DataStream {
    void write(String data);
}
```

### Step 2: Create the Concrete Component

```java
// Concrete Component
public class FileDataStream implements DataStream {
    @Override
    public void write(String data) {
        System.out.println("Writing data to file: " + data);
    }
}
```

### Step 3: Create the Decorator Class

```java
// Decorator Class
public class DataStreamDecorator implements DataStream {
    protected DataStream wrappedDataStream;

    public DataStreamDecorator(DataStream dataStream) {
        this.wrappedDataStream = dataStream;
    }

    @Override
    public void write(String data) {
        wrappedDataStream.write(data);
    }
}
```

### Step 4: Implement Concrete Decorators

#### EncryptionDecorator

```java
// Concrete Decorator for Encryption
public class EncryptionDecorator extends DataStreamDecorator {

    public EncryptionDecorator(DataStream dataStream) {
        super(dataStream);
    }

    @Override
    public void write(String data) {
        String encryptedData = encrypt(data);
        super.write(encryptedData);
    }

    private String encrypt(String data) {
        return "Encrypted(" + data + ")";
    }
}
```

#### CompressionDecorator

```java
// Concrete Decorator for Compression
public class CompressionDecorator extends DataStreamDecorator {

    public CompressionDecorator(DataStream dataStream) {
        super(dataStream);
    }

    @Override
    public void write(String data) {
        String compressedData = compress(data);
        super.write(compressedData);
    }

    private String compress(String data) {
        return "Compressed(" + data + ")";
    }
}
```

### Step 5: Use the Decorator Pattern

```java
public class Main {
    public static void main(String[] args) {
        // Basic file data stream
        DataStream fileDataStream = new FileDataStream();

        // Encrypting data stream
        DataStream encryptedStream = new EncryptionDecorator(fileDataStream);

        // Compressing and encrypting data stream
        DataStream compressedAndEncryptedStream = new CompressionDecorator(encryptedStream);

        // Writing data with each configuration
        System.out.println("Writing with basic data stream:");
        fileDataStream.write("Sample Data"); 
        // Output: Writing data to file: Sample Data

        System.out.println("
Writing with encrypted data stream:");
        encryptedStream.write("Sample Data"); 
        // Output: Writing data to file: Encrypted(Sample Data)

        System.out.println("
Writing with compressed and encrypted data stream:");
        compressedAndEncryptedStream.write("Sample Data"); 
        // Output: Writing data to file: Compressed(Encrypted(Sample Data))
    }
}
```

---

## Explanation of How Decorator Solves the Problem

1. **Dynamic Extension of Functionality**: By stacking decorators, new functionality is added to `DataStream` at runtime without modifying the base class.
2. **Flexible Combination of Features**: Decorators can be combined in any order to achieve different combinations of behavior, allowing for maximum flexibility.
3. **Avoids Class Explosion**: Instead of creating separate classes for every combination of features, decorators enable feature composition at runtime.

---

## Benefits of the Decorator Pattern

- **Flexible and Reusable**: Decorators can be combined in any order, creating flexible and reusable functionality extensions.
- **Promotes Open-Closed Principle**: The pattern enables extending functionality without modifying existing code.
- **No Class Explosion**: Avoids creating multiple subclasses for every possible combination of functionality.

## Drawbacks of the Decorator Pattern

- **Increased Complexity**: Multiple layers of decorators can make it difficult to understand the behavior of the final wrapped object.
- **Order Sensitivity**: The order of decorators affects the output, which can be confusing to manage.
  
---

## Summary

The Decorator pattern allows you to add functionality to objects dynamically, promoting flexibility and composition over inheritance. This pattern is especially useful in scenarios where different combinations of behaviors are needed, as it allows behavior to be added in a flexible, reusable way.

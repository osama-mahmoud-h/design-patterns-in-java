
# Singleton Design Patterns in Java

The **Singleton design pattern** is a **creational pattern** that ensures a class has only one instance and provides a global point of access to it. This pattern is especially useful in scenarios where a single object is needed to coordinate actions across a system, like managing configuration settings, database connections, or logging.

## Table of Contents

1. [Why Use Singleton?](#why-use-singleton)
2. [Types of Singleton Implementations](#types-of-singleton-implementations)
    - [Non-Thread Safe Singleton](#1-non-thread-safe-singleton)
    - [Thread-Safe Singleton (Synchronized Method)](#2-thread-safe-singleton-synchronized-method)
    - [Thread-Safe Singleton (Double-Checked Locking)](#3-thread-safe-singleton-double-checked-locking)
    - [Enum Singleton (Modern Java)](#4-enum-singleton-modern-java)
3. [Common Problems and Solutions](#common-problems-and-solutions)

---

### Why Use Singleton?

- **Control Over Instance Creation**: Singleton ensures that only one instance of a class exists, avoiding duplication of effort.
- **Consistency**: Useful when a single instance needs to control shared resources, configuration, or logging, ensuring all parts of the program work with the same data.
- **Efficient Resource Usage**: Singleton avoids creating multiple instances, reducing memory consumption and resource usage.

### Types of Singleton Implementations

#### 1. Non-Thread Safe Singleton

This basic implementation is simple but **not thread-safe**. Multiple threads can create separate instances if they access `getInstance()` at the same time.

```java
public class NonThreadSafeSingleton {
    private static NonThreadSafeSingleton instance;
    public static int count = 0;

    private NonThreadSafeSingleton() {
        count++;
    }

    public static NonThreadSafeSingleton getInstance() {
        if (instance == null) {
            // Simulate some delay
            try {
                Thread.sleep(100); // This makes it more likely to see the problem
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new NonThreadSafeSingleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Non-Thread Safe Singleton! Count: " + count);
    }

    public static void main(String[] args) {
        // Create multiple threads that try to get the Singleton instance
        Runnable task = () -> {
            NonThreadSafeSingleton singleton = NonThreadSafeSingleton.getInstance();
            singleton.showMessage();
        };

        // Start multiple threads
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

```

#### 2. Thread-Safe Singleton (Synchronized Method)

This version uses a **synchronized method** to ensure only one thread accesses `getInstance()` at a time.

```java
public class ThreadSafeSingleton {
    private static ThreadSafeSingleton instance;
    public static int count = 0;

    private ThreadSafeSingleton() {
        count++;
    }

    // Synchronized method to make it thread-safe
    public static synchronized ThreadSafeSingleton getInstance() {
        if (instance == null) {
            // Simulate some delay
            try {
                Thread.sleep(100); // Simulate processing time
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new ThreadSafeSingleton();
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Thread Safe Singleton! Count: " + count);
    }

    public static void main(String[] args) {
        // Create multiple threads that try to get the Singleton instance
        Runnable task = () -> {
            ThreadSafeSingleton singleton = ThreadSafeSingleton.getInstance();
            singleton.showMessage();
        };

        // Start multiple threads
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

```

#### 3. Thread-Safe Singleton (Double-Checked Locking)

Optimizes performance by only synchronizing when `instance` is `null`, using **double-checked locking**.

```java

public class DoubleCheckedLockingSingleton {
    private static volatile DoubleCheckedLockingSingleton instance;
    public static int count = 0;

    private DoubleCheckedLockingSingleton() {
        count++;
    }

    public static DoubleCheckedLockingSingleton getInstance() {
        if (instance == null) {
            synchronized (DoubleCheckedLockingSingleton.class) {
                if (instance == null) {
                    // Simulate some delay
                    try {
                        Thread.sleep(100); // Simulate processing time
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new DoubleCheckedLockingSingleton();
                }
            }
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Double-Checked Locking Singleton! Count: " + count);
    }

    public static void main(String[] args) {
        // Create multiple threads that try to get the Singleton instance
        Runnable task = () -> {
            DoubleCheckedLockingSingleton singleton = DoubleCheckedLockingSingleton.getInstance();
            singleton.showMessage();
        };

        // Start multiple threads
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

```

#### 4. Enum Singleton (Modern Java)

Enums provide a simple, robust way to implement a Singleton that is **thread-safe** by design.

```java
public enum EnumSingleton {
    INSTANCE;

    private int count;

    EnumSingleton() {
        // Initializing count in constructor
        this.count = new Random().nextInt()%1000 + 10;
    }

    public void showMessage() {
        System.out.println("Hello from Enum Singleton! Count: " + count);
    }

    public static void main(String[] args) {
        // Multiple threads attempting to access the singleton
        Runnable task = () -> {
            EnumSingleton singleton = EnumSingleton.INSTANCE;
            singleton.showMessage();
        };

        // Start multiple threads to simulate access
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        Thread thread3 = new Thread(task);
        thread1.start();
        thread2.start();
        thread3.start();
    }
}

```

---

### Common Problems and Solutions

- **Thread Safety**: Ensure that only one instance is created even when multiple threads access the `getInstance()` method. Solutions include synchronized methods, double-checked locking, or using enums.
- **Serialization**: With non-enum singletons, deserialization can create a new instance. To solve this, override `readResolve()` to return the existing instance.
- **Reflection**: Reflection can break Singleton by allowing private constructors to be called. This can be mitigated by throwing an exception in the constructor if an instance already exists or by using enums.

---

## How to Test Each Singleton

1. Compile and run each Singleton class separately.
2. Use multiple threads to verify thread safety where applicable.

---

This README provides code examples and explanations for each type of Singleton implementation, allowing you to compare and choose the best approach for your Java projects.
package creational.singleton;

import java.util.Random;

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


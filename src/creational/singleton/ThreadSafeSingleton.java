package creational.singleton;

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


package creational.singleton;

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

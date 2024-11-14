package creational.singleton;

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

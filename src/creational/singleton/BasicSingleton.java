package creational.singleton;

public class BasicSingleton {
    // Private static instance of the Singleton class
    private static BasicSingleton instance;
    public static int count = 0;

    // Private constructor to prevent instantiation from outside the class
    private BasicSingleton() {
        count++;
    }

    // Public method to provide access to the instance
    public static BasicSingleton getInstance() {
        if (instance == null) { // Check if instance is null
            instance = new BasicSingleton(); // Create new instance if it doesn't exist
        }
        return instance;
    }

    public void showMessage() {
        System.out.println("Hello from Singleton! "+ count);
    }

    public static void main(String[] args) {
        BasicSingleton
                singleton = BasicSingleton.getInstance(),
                singleton2 = BasicSingleton.getInstance();
        singleton.showMessage();
        singleton2.showMessage();
    }
}
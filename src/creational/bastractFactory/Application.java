package creational.bastractFactory;

public class Application {
    private final Button button;
    private final Checkbox checkbox;

    public Application(GUIFactory factory) {
        button = factory.createButton();
        checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }

    public static void main(String[] args) {
        GUIFactory factory = null;

        // Example: Based on the OS, choose a factory
        String os = OsType.WINDOWS.toString(); // Or dynamically determine this

        if (os.equalsIgnoreCase(OsType.WINDOWS.toString())) {
            factory = new WindowsFactory();
        } else if (os.equalsIgnoreCase(OsType.MAC.toString())) {
            factory = new MacFactory();
        }else if (os.equalsIgnoreCase(OsType.LINUX.toString())){
            throw new RuntimeException("not Implemented os");
        }else {
            throw new RuntimeException("Invalid os");
        }

        // Create an application with the chosen factory
        Application app = new Application(factory);
        app.paint();  // Output depends on the OS: Windows or Mac
    }
}


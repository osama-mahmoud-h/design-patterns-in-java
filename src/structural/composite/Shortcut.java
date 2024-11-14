package structural.composite;

public class Shortcut implements FileSystemComponent {
    private final String name;

    public Shortcut(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("Shortcut: " + name);
    }

    @Override
    public void delete() {
        System.out.println("Deleting shortcut: " + name);
    }
}

package structural.composite;

// Leaf class
public class File implements FileSystemComponent {
    private final String name;

    public File(String name) {
        this.name = name;
    }

    @Override
    public void showDetails() {
        System.out.println("File: " + name);
    }

    @Override
    public void delete() {
        System.out.println("Deleting file: " + name);
    }
}


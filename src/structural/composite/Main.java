package structural.composite;

public class Main {
    public static void main(String[] args) {
        // Create individual files
        FileSystemComponent file1 = new File("Document.docx");
        FileSystemComponent file2 = new File("Picture.png");
        FileSystemComponent shortcut = new Shortcut("Shortcut to Document.docx");

        // Create folders and add files/folders to them
        Folder mainFolder = new Folder("MainFolder");
        Folder subFolder = new Folder("SubFolder");

        mainFolder.addComponent(file1);  // Add file to main folder
        mainFolder.addComponent(subFolder);  // Add subfolder to main folder
        subFolder.addComponent(file2);  // Add file to subfolder
        subFolder.addComponent(shortcut);  // Add shortcut to subfolder

        // Display structure and delete contents
        System.out.println("Showing file structure:");
        mainFolder.showDetails();

        System.out.println("\nDeleting file structure:");
        mainFolder.delete();
    }
}

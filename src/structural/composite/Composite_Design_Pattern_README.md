
# Composite Design Pattern

## Problem Definition

The **Composite** design pattern is a **structural pattern** that allows you to compose objects into tree-like structures to represent hierarchies. This pattern enables clients to treat individual objects and compositions of objects uniformly. The Composite pattern is especially useful when dealing with a structure of objects in a tree-like hierarchy, such as file systems, UI components, or organizational structures.

### Problems Before Composite Pattern

Imagine you’re designing a file management system where files and folders are represented as objects. Both files and folders need to support operations like `open`, `delete`, and `display information`. A file is a single entity, while a folder can contain multiple files or even other folders, forming a hierarchical structure.

#### Problems Before Composite Pattern

1. **Inconsistent Interface**: Files and folders have similar operations (like `open`, `delete`), but their implementations and structure differ.
2. **Complexity in Handling Hierarchies**: Without Composite, you might need to handle folders and files separately, leading to complex code with many conditional statements.
3. **Limited Scalability**: Adding new components (like shortcuts or symlinks) becomes challenging, as the code is tightly coupled with specific types of objects.

---

## Solution: Composite Pattern

The **Composite pattern** allows you to build a tree structure of objects where individual objects (leaves) and groups of objects (composites) are treated uniformly. In this pattern:

- **Component**: Declares a common interface for both simple (leaf) and complex (composite) objects.
- **Leaf**: Represents individual objects that don’t have any children.
- **Composite**: Contains child components and defines behavior for handling child objects.

This pattern provides a single interface through which clients can interact with both individual objects and groups of objects without knowing their exact type.

---

## How the Composite Pattern Works

1. **Component Interface**: Declares operations shared by leaf and composite objects. For example, `open`, `delete`, or `displayInfo`.
2. **Leaf Class**: Implements the `Component` interface but doesn’t contain children, such as individual files in a file system.
3. **Composite Class**: Implements the `Component` interface and holds references to child components, allowing nested groups of objects, such as folders in a file system.

---

## Example: Composite Pattern in Java

Let’s implement a file management system with `File` and `Folder` classes, where `Folder` can contain multiple files and/or folders.

### Step 1: Define the Component Interface

```java
// Component interface
public interface FileSystemComponent {
    void showDetails();
    void delete();
}
```

### Step 2: Implement the Leaf Class (File)

```java
// Leaf class
public class File implements FileSystemComponent {
    private String name;

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
```

### Step 3: Implement the Composite Class (Folder)

```java
// Composite class
import java.util.ArrayList;
import java.util.List;

public class Folder implements FileSystemComponent {
    private String name;
    private List<FileSystemComponent> components = new ArrayList<>();

    public Folder(String name) {
        this.name = name;
    }

    public void addComponent(FileSystemComponent component) {
        components.add(component);
    }

    public void removeComponent(FileSystemComponent component) {
        components.remove(component);
    }

    @Override
    public void showDetails() {
        System.out.println("Folder: " + name);
        for (FileSystemComponent component : components) {
            component.showDetails();
        }
    }

    @Override
    public void delete() {
        System.out.println("Deleting folder: " + name);
        for (FileSystemComponent component : components) {
            component.delete();
        }
    }
}
```

### Step 4: Use the Composite Pattern

```java
public class Main {
    public static void main(String[] args) {
        // Create individual files
        FileSystemComponent file1 = new File("Document.docx");
        FileSystemComponent file2 = new File("Picture.png");

        // Create folders and add files/folders to them
        Folder mainFolder = new Folder("MainFolder");
        Folder subFolder = new Folder("SubFolder");

        mainFolder.addComponent(file1);  // Add file to main folder
        mainFolder.addComponent(subFolder);  // Add subfolder to main folder
        subFolder.addComponent(file2);  // Add file to subfolder

        // Display structure and delete contents
        System.out.println("Showing file structure:");
        mainFolder.showDetails();

        System.out.println("
Deleting file structure:");
        mainFolder.delete();
    }
}
```

---

## Explanation of How Composite Solves the Problem

1. **Uniform Treatment of Objects**: The `FileSystemComponent` interface allows both files and folders to be treated as `FileSystemComponent` objects, providing a uniform way to interact with them.
2. **Simplified Hierarchical Management**: By using `Folder` to store child components, the Composite pattern lets you build and manage hierarchical structures, such as nested folders and files, without complex conditional logic.
3. **Flexible and Extensible**: New component types (e.g., symbolic links) can be added by implementing `FileSystemComponent` without affecting the existing code.

---

## Benefits of the Composite Pattern

- **Uniformity**: Allows individual objects and groups of objects to be treated uniformly, reducing code complexity.
- **Simplifies Hierarchical Structures**: Facilitates the management of hierarchical structures, such as file systems or organizational charts.
- **Extensibility**: New component types can be added easily by implementing the `Component` interface.

## Drawbacks of the Composite Pattern

- **Complexity with Complex Hierarchies**: As the hierarchy grows, managing the relationships between components can become complex.
- **Inappropriate for Flat Structures**: If there’s no hierarchy, using Composite might add unnecessary complexity.

---

## Summary

The Composite pattern provides a solution for managing tree-like structures by allowing individual objects (leaves) and groups of objects (composites) to be treated the same. It’s particularly useful for scenarios involving hierarchical structures, such as file systems, where you need a flexible and unified approach to interact with both simple and complex components.

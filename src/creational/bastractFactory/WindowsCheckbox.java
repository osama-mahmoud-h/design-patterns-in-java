package creational.bastractFactory;

class WindowsCheckbox implements Checkbox {
    @Override
    public void paint() {
        System.out.println("Rendering a Windows-styled checkbox.");
    }
}
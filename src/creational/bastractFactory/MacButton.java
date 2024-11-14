package creational.bastractFactory;

public class MacButton implements Button{
    @Override
    public void paint() {
        System.out.println("Rendering a MacOS-styled button.");
    }
}

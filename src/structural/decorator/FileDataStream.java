package structural.decorator;

// Concrete Component
public class FileDataStream implements DataStream {
    @Override
    public void write(String data) {
        System.out.println("Writing data to file: " + data);
    }
}


package structural.decorator;

// Concrete Decorator for Compression
public class CompressionDecorator extends DataStreamDecorator {

    public CompressionDecorator(DataStream dataStream) {
        super(dataStream);
    }

    @Override
    public void write(String data) {
        String compressedData = compress(data);
        super.write(compressedData);
    }

    private String compress(String data) {
        return "Compressed(" + data + ")";
    }
}

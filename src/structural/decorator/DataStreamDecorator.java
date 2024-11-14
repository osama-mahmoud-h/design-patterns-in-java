package structural.decorator;

// Decorator Class
public class DataStreamDecorator implements DataStream {
    protected DataStream wrappedDataStream;

    public DataStreamDecorator(DataStream dataStream) {
        this.wrappedDataStream = dataStream;
    }

    @Override
    public void write(String data) {
        wrappedDataStream.write(data);
    }
}

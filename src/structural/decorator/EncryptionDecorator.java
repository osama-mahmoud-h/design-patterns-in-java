package structural.decorator;

// Concrete Decorator for Encryption
public class EncryptionDecorator extends DataStreamDecorator {

    public EncryptionDecorator(DataStream dataStream) {
        super(dataStream);
    }

    @Override
    public void write(String data) {
        String encryptedData = encrypt(data);
        super.write(encryptedData);
    }

    private String encrypt(String data) {
        return "Encrypted(" + data + ")";
    }
}

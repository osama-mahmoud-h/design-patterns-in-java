package structural.decorator;

public class Main {
    public static void main(String[] args) {
        // Basic file data stream
        DataStream fileDataStream = new FileDataStream();

        // Encrypting data stream
        DataStream encryptedStream = new EncryptionDecorator(fileDataStream);

        // Compressing and encrypting data stream
        DataStream compressedAndEncryptedStream = new CompressionDecorator(encryptedStream);

        // Writing data with each configuration
        System.out.println("Writing with basic data stream:");
        fileDataStream.write("Sample Data");
        // Output: Writing data to file: Sample Data

        System.out.println("\nWriting with encrypted data stream:");
        encryptedStream.write("Sample Data");
        // Output: Writing data to file: Encrypted(Sample Data)

        System.out.println("\nWriting with compressed and encrypted data stream:");
        compressedAndEncryptedStream.write("Sample Data");
        // Output: Writing data to file: Compressed(Encrypted(Sample Data))
    }
}

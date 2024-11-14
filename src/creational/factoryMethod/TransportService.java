package creational.factoryMethod;

public class TransportService {
    private Vehicle vehicle;

    // Constructor takes a factory instead of a type string
    public TransportService(VehicleFactory factory) {
        this.vehicle = factory.createVehicle();
    }

    public void startDelivery() {
        vehicle.deliver();
    }

    public static void main(String[] args) {
        VehicleFactory carFactory = new CarFactory();
        TransportService carService = new TransportService(carFactory);
        carService.startDelivery();  // Output: Delivering by car.

        VehicleFactory bikeFactory = new BikeFactory();
        TransportService bikeService = new TransportService(bikeFactory);
        bikeService.startDelivery();  // Output: Delivering by bike.
    }
}


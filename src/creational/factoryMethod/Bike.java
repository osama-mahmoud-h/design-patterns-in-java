package creational.factoryMethod;

class Bike implements Vehicle {
    @Override
    public void deliver() {
        System.out.println("Delivering by bike.");
    }
}

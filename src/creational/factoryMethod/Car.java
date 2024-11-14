package creational.factoryMethod;

class Car implements Vehicle {
    @Override
    public void deliver() {
        System.out.println("Delivering by car.");
    }
}
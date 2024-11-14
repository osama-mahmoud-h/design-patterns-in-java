package creational.builder;

public class House {
    private final String foundation;
    private final String walls;
    private final String roof;
    private final int numberOfRooms;
    private final boolean hasGarage;
    private final boolean hasSwimmingPool;

    // Private constructor so only the Builder can create instances
    private House(HouseBuilder builder) {
        this.foundation = builder.foundation;
        this.walls = builder.walls;
        this.roof = builder.roof;
        this.numberOfRooms = builder.numberOfRooms;
        this.hasGarage = builder.hasGarage;
        this.hasSwimmingPool = builder.hasSwimmingPool;
    }

    @Override
    public String toString() {
        return "House with " + foundation + " foundation, " + walls + " walls, " +
                roof + " roof, " + numberOfRooms + " rooms, " +
                (hasGarage ? "a garage" : "no garage") + ", " +
                (hasSwimmingPool ? "a swimming pool" : "no swimming pool");
    }

    // Static inner Builder class
    public static class HouseBuilder {
        private String foundation;
        private String walls;
        private String roof;
        private int numberOfRooms;
        private boolean hasGarage;
        private boolean hasSwimmingPool;

        public HouseBuilder setFoundation(String foundation) {
            this.foundation = foundation;
            return this;
        }

        public HouseBuilder setWalls(String walls) {
            this.walls = walls;
            return this;
        }

        public HouseBuilder setRoof(String roof) {
            this.roof = roof;
            return this;
        }

        public HouseBuilder setNumberOfRooms(int numberOfRooms) {
            this.numberOfRooms = numberOfRooms;
            return this;
        }

        public HouseBuilder setGarage(boolean hasGarage) {
            this.hasGarage = hasGarage;
            return this;
        }

        public HouseBuilder setSwimmingPool(boolean hasSwimmingPool) {
            this.hasSwimmingPool = hasSwimmingPool;
            return this;
        }

        public House build() {
            return new House(this);
        }
    }

    public static void main(String[] args) {
        House house = new House.HouseBuilder()
                .setFoundation("Concrete")
                .setWalls("Brick")
                .setRoof("Shingle")
                .setNumberOfRooms(4)
                .setGarage(true)
                .setSwimmingPool(false)
                .build();

        System.out.println(house);
        // Output: House with Concrete foundation, Brick walls, Shingle roof, 4 rooms, a garage, no swimming pool
    }
}

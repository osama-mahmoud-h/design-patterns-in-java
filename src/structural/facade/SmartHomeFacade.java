package structural.facade;

public class SmartHomeFacade {
    private final Lights lights;
    private final Thermostat thermostat;
    private final SecuritySystem securitySystem;

    public SmartHomeFacade(Lights lights, Thermostat thermostat, SecuritySystem securitySystem) {
        this.lights = lights;
        this.thermostat = thermostat;
        this.securitySystem = securitySystem;
    }

    public void startDay() {
        System.out.println("Starting the day...");
        lights.turnOn();
        thermostat.setTemperature(72);
        securitySystem.deactivate();
    }

    public void endDay() {
        System.out.println("Ending the day...");
        lights.turnOff();
        thermostat.setTemperature(65);
        securitySystem.activate();
    }

    public static void main(String[] args) {
        // Create subsystems
        Lights lights = new Lights();
        Thermostat thermostat = new Thermostat();
        SecuritySystem securitySystem = new SecuritySystem();

        // Create the facade
        SmartHomeFacade smartHome = new SmartHomeFacade(lights, thermostat, securitySystem);

        // Use the facade to perform complex actions
        smartHome.startDay();   // Starts the day with a single call
        smartHome.endDay();     // Ends the day with a single call
    }
}

package eu.luminis.kenteken.search;

public class Vehicle {
    private final String make;
    private final String model;
    private final String color;
    private final int numberOfCylinders;

    public Vehicle(final String make, final String model, final String color, final int numberOfCylinders) {
        this.make = make;
        this.model = model;
        this.color = color;
        this.numberOfCylinders = numberOfCylinders;
    }
    
    public String getMake() {
        return make;
    }
    
    public String getModel() {
        return model;
    }
    
    public String getColor() {
        return color;
    }
    
    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }
}

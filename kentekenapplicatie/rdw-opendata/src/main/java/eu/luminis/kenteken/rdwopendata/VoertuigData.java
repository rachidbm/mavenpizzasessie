package eu.luminis.kenteken.rdwopendata;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class VoertuigData {
    
    private String make;
    private String model;
    private String color;
    private int numberOfCylinders;
    
    @JsonProperty("merk")
    public void setMake(final String make) {
        this.make = make;
    }
    public String getMake() {
        return make;
    }
    
    @JsonProperty("handelsbenaming")
    public void setModel(final String model) {
        this.model = model;
    }
    public String getModel() {
        return model;
    }
    
    @JsonProperty("eerste_kleur")
    public void setColor(final String color) {
        this.color = color;
    }
    public String getColor() {
        return color;
    }
    
    @JsonProperty("aantal_cilinders")
    public void setNumberOfCylinders(final int numberOfCylinders) {
        this.numberOfCylinders = numberOfCylinders;
    }
    public int getNumberOfCylinders() {
        return numberOfCylinders;
    }
}

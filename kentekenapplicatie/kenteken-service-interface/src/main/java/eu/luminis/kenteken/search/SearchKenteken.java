package eu.luminis.kenteken.search;

public interface SearchKenteken {

    Vehicle findVehicle(String kenteken) throws NoVehicleFoundException;
}

package eu.luminis.kenteken.search;

public class NoVehicleFoundException extends Exception {

    private static final long serialVersionUID = 1L;

    public NoVehicleFoundException(final String kenteken) {
        super(String.format("No vehicle with licence plate %s was found", kenteken));
    }
}

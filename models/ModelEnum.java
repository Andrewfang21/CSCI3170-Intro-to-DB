package models;

public enum ModelEnum {
    VEHICLES("vehicles", Vehicle.class),
    PASSENGERS("passengers", Passenger.class),
    DRIVERS("drivers", Driver.class),
    TAXI_STOP("taxi_stops", TaxiStop.class),
    TRIP("trips", Trip.class),
    REQUEST("requests", null);

    private String name;
    private Class<?> className;

    ModelEnum(String name, Class<?> className) {
        this.name = name;
        this.className = className;
    }

    public String getName() {
        return name;
    }

    public Class<?> getClassName() {
        return className;
    }
}

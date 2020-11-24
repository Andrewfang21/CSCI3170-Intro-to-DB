package models;

public enum ModelEnum {
    VEHICLES("vehicle", Vehicle.class),
    PASSENGERS("passenger", Passenger.class),
    DRIVERS("driver", Driver.class),
    TRIP("trip", Trip.class),
    REQUEST("request", Request.class),
    TAXI_STOP("taxi_stop", TaxiStop.class);

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

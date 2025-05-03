package org.example.DomainModel;

public class DeliveryRoute {
    private final String from;
    private final String destination;

    public DeliveryRoute(String from, String destination) {
        this.from = from;
        this.destination = destination;
    }

    public String getFrom() {
        return from;
    }

    public String getDestination() {
        return destination;
    }

    @Override
    public String toString() {
        return "DeliveryRoute{" +
                "from='" + from + '\'' +
                ", destination='" + destination + '\'' +
                '}';
    }
}

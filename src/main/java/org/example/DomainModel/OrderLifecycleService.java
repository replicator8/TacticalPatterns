package org.example.DomainModel;

public class OrderLifecycleService {
    public void activateTransportationOrder(TransportationOrder to) {
        to.setOrderStatus(OrderStatus.ACTIVE);
    }

    public void deactivateTransportationOrder(TransportationOrder to) {
        to.setOrderStatus(OrderStatus.INACTIVE);
    }

    public void completeTransportationOrder(TransportationOrder to) {
        to.setOrderStatus(OrderStatus.COMPLETE);
    }

    public void closeTransportationOrder(TransportationOrder to) {
        to.setOrderStatus(OrderStatus.CLOSED);
    }
}

package org.example.DomainModel;

public class FraudDetectionService {
    public void checkActivity(TransportationOrder to) {
        if (to.getOrderStatus() != OrderStatus.ACTIVE) {
            throw new IllegalStateException("Заявка должна быть активной!");
        }

        for (Message m: to.getMessages()) {
            if (m.getMessage().contains("SELECT") || m.getMessage().contains("UPDATE") || m.getMessage().contains("DELETE")
            || m.getMessage().contains("<?xml>") || m.getMessage().contains("<.js>") || m.getMessage().contains("DROP")) {
                to.setOrderStatus(OrderStatus.CLOSED);
                System.out.println("Заявка закрыта из-за подозрительной активности!");
                return;
            }
        }
        System.out.println("Подозрений нет!");
    }
}

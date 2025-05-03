package org.example.DomainModel;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TransportationOrder {
    private final int id;
    private final List<Message> messages;
    private final List<Attachment> attachments;
    private OrderStatus orderStatus;
    private final DeliveryRoute deliveryRoute;
    private LocalDateTime lastActivity;

    public TransportationOrder(int id, DeliveryRoute deliveryRoute) {
        this.id = id;
        this.messages = new ArrayList<>();
        this.attachments = new ArrayList<>();
        this.orderStatus = OrderStatus.CREATED;
        this.deliveryRoute = deliveryRoute;
        lastActivity = LocalDateTime.now();
    }

    public List<Message> getMessages() {
        return messages;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public DeliveryRoute getDeliveryRoute() {
        return deliveryRoute;
    }

    public int getId() {
        return id;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public void addMessage(Message message, List<Attachment> attachments) {
        if (message == null) {
            throw new IllegalArgumentException("Пустое сообщение");
        }

        if (orderStatus != OrderStatus.ACTIVE) {
            throw new IllegalArgumentException("Нельзя добавить сообщение к неактивной заявке");
        }

        if (attachments == null) {
            messages.add(message);
            checkActivity();
            System.out.println("Сообщение успешно добавлено");
            return;
        }

        message.addAttachment(attachments);
        messages.add(message);
        this.attachments.addAll(attachments);
        checkActivity();

        System.out.println("Сообщение успешно добавлено");
    }

    public void approveMessageByClient(int messageId) {
        Message message = null;

        for (Message m: messages) {
            if (m.getId() == messageId) {
                message = m;
            }
        }

        if (message == null) {
            throw new IllegalArgumentException("Такого сообщения в заявке нет!");
        }

        if (message.getAuthor().equals(Author.CLIENT)) {
            throw new IllegalArgumentException("Подтверждение сообщения доступно только противоположной стороне!");
        }
        messages.remove(message);
        message.setApprovedByClient(true);
        messages.add(message);
        checkActivity();
        System.out.println("Сообщение успешно подтверждено клиентом!");
    }

    public void approveMessageByDriver(int messageId) {
        Message message = null;

        for (Message m: messages) {
            if (m.getId() == messageId) {
                message = m;
            }
        }

        if (message == null) {
            throw new IllegalArgumentException("Такого сообщения в заявке нет!");
        }
        System.out.println(message.getAuthor());
        if (message.getAuthor().equals(Author.DRIVER)) {
            throw new IllegalArgumentException("Подтверждение сообщения доступно только противоположной стороне!");
        }

        messages.remove(message);
        message.setApprovedByDriver(true);
        messages.add(message);
        checkActivity();
        System.out.println("Сообщение успешно подтверждено водителем!");
    }

    public void autoCloseOrderDueInactivity() {
        if ((lastActivity.plusDays(1)).isBefore(LocalDateTime.now())) {
            orderStatus = OrderStatus.INACTIVE;
            System.out.println("Заявка закрыта из-занеактивности!");
        }
    }

    public void checkActivity() {
        lastActivity = LocalDateTime.now();
    }

    @Override
    public String toString() {
        return "TransportationOrder{" +
                "message=" + messages +
                ", attachment=" + attachments +
                ", orderStatus=" + orderStatus +
                ", deliveryRoute=" + deliveryRoute +
                '}';
    }
}

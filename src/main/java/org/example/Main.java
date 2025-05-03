package org.example;

import org.example.ActiveRecord.Driver;
import org.example.ActiveRecord.DriverFactory;
import org.example.ActiveRecord.DriverRepository;
import org.example.ActiveRecord.Shift;
import org.example.DomainModel.*;
import org.example.TransactionScript.ScheduleTransactionScript;
import java.time.LocalDateTime;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        // Создание фабрики и репозитория
        DriverRepository repository = new DriverRepository();
        Driver driver = DriverFactory.createDriver("John Doe", true);
        repository.save(driver);

        // Создание транзакционного скрипта
        ScheduleTransactionScript transactionScript = new ScheduleTransactionScript(repository);

        // Добавление смен
        transactionScript.addShiftToDriver(driver.getId(), LocalDateTime.of(2023, 12, 14, 10, 0), LocalDateTime.of(2023, 12, 14, 12, 0));
        transactionScript.addShiftToDriver(driver.getId(), LocalDateTime.of(2023, 12, 14, 14, 0), LocalDateTime.of(2023, 12, 15, 16, 0));

        // Проверка расписания
        List<Shift> sessions = transactionScript.getShiftsForDriverOnDate(driver.getId(), LocalDateTime.of(2023, 12, 14, 0, 0));
        System.out.println("Занятия на 2023-12-14: " + sessions);

        // Проверка доступности
        boolean isAvailable = transactionScript.checkDriverAvailability(driver.getId(), LocalDateTime.of(2023, 12, 14, 11, 0));
        System.out.println("Доступность на 2023-12-14 11:00: " + isAvailable);

        // Создание заявки на перевозку
        InMemoryOrderRepository orderRepository = new InMemoryOrderRepository();
        FraudDetectionService fds = new FraudDetectionService();
        OrderLifecycleService ols = new OrderLifecycleService();
        DeliveryRoute dr = new DeliveryRoute("Kaliningrad", "Moscow, Lubertsy");
        TransportationOrder to = new TransportationOrder(1, dr);
        orderRepository.save(to);

        // Добавление сообщения
        Attachment attachment1 = new Attachment(1, "photo.jpeg");
        Attachment attachment2 = new Attachment(2, "photo.svg");
        Message message = new Message(1, "Hi where my pizza", Author.CLIENT);

//        to.addMessage(message, List.of(attachment1, attachment2));

        // Изменим состояние заявки на ACTIVE
        ols.activateTransportationOrder(to);

        to.addMessage(message, List.of(attachment1, attachment2));
        System.out.println(to.getMessages());

        // Водитель подтверждает сообщение
        to.approveMessageByDriver(message.getId());
        System.out.println(message);

//        to.approveMessageByClient(message.getId());

        to.autoCloseOrderDueInactivity();

        // Проверим подозрительную активность
        fds.checkActivity(to);
        Message message2 = new Message(2, "SELECT * FROM USERS", Author.CLIENT);
        to.addMessage(message2, null);

        fds.checkActivity(to);
        System.out.println(to.getOrderStatus());
    }
}
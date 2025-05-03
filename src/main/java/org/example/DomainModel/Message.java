package org.example.DomainModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Message {
    private int id;
    private String message;
    private final Author author;
    private final List<Attachment> attachments;
    private boolean approvedByClient;
    private boolean approvedByDriver;

    public Message(int id, String message, Author author) {
        this.attachments = new ArrayList<>();
        this.id = id;
        this.author = author;
        this.message = message;
        this.approvedByClient = false;
        this.approvedByDriver = false;
    }

    public String getMessage() {
        return message;
    }

    public Author getAuthor() {
        return author;
    }

    public List<Attachment> getAttachments() {
        return attachments;
    }

    public boolean isApprovedByClient() {
        return approvedByClient;
    }

    public boolean isApprovedByDriver() {
        return approvedByDriver;
    }

    public int getId() {
        return id;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public void setApprovedByClient(boolean approvedByClient) {
        this.approvedByClient = approvedByClient;
    }

    public void setApprovedByDriver(boolean approvedByDriver) {
        this.approvedByDriver = approvedByDriver;
    }

    public void addAttachment(List<Attachment> attachments) {
        for (Attachment a: attachments) {
            if (a == null || Objects.equals(a.getContent(), "")) {
                throw new IllegalArgumentException("Пустое вложение");
            }
            this.attachments.add(a);
        }
    }

    @Override
    public String toString() {
        return "Message{" +
                "id=" + id +
                ", message='" + message + '\'' +
                ", author='" + author.toString() + '\'' +
                ", attachments=" + attachments +
                ", approvedByClient=" + approvedByClient +
                ", approvedByDriver=" + approvedByDriver +
                '}';
    }
}

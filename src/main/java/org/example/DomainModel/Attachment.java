package org.example.DomainModel;

public class Attachment {
    private int id;
    private String content;

    public Attachment(int id, String content) {
        this.id = id;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Attachment{" +
                "id=" + id +
                ", content='" + content + '\'' +
                '}';
    }
}

package model;

import java.util.Date;

public class News {

    private String title;
    private String datePublication;
    private String topic;
    private String text;

    public News() {
    }

    public News(String title, String datePublication, String topic, String text) {
        this.title = title;
        this.datePublication = datePublication;
        this.topic = topic;
        this.text = text;
    }

    @Override
    public String toString() {
        return "�������� �������: " + title +
                "\n ���� ����������: " + datePublication +
                "\n ����: " + topic +
                "\n ����� ������: " + text;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String datePublication) {
        this.datePublication = datePublication;
    }

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

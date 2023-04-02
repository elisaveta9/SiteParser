package model;

import java.util.Date;

public class News {

    private String title;
    private String datePublication;
    private String country;
    private String text;

    public News() {
    }

    public News(String title, String datePublication, String country, String text) {
        this.title = title;
        this.datePublication = datePublication;
        this.country = country;
        this.text = text;
    }

    @Override
    public String toString() {
        return "Название новости: " + title +
                "\n Дата публикации: " + datePublication +
                "\n Страна: " + country +
                "\n Текст статьи: " + text;
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

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

package model;

public class Feedback {

    private String username;
    private int rating;
    private String titleFeedback;
    private String advantages;
    private String disadvantages;
    private String body;

    public Feedback() {
    }

    public Feedback(String username, int rating, String titleFeedback, String advantages, String disadvantages, String body) {
        this.username = username;
        this.rating = rating;
        this.titleFeedback = titleFeedback;
        this.advantages = advantages;
        this.disadvantages = disadvantages;
        this.body = body;
    }

    @Override
    public String toString() {
        if (advantages.isEmpty() || advantages.equals(""))
            advantages = "�� �������";
        if (disadvantages.isEmpty() || disadvantages.equals(""))
            disadvantages = "�� �������";
        if (!titleFeedback.isEmpty() && !titleFeedback.equals(""))
            return "��� ������������: " + username + "\n" +
                "��������� ������: " + titleFeedback + "\n" +
                "������� ��������: " + rating + " �� 5" + "\n" +
                "����������� ��������: " + advantages + "\n" +
                "����������: " + disadvantages + "\n" +
                "��������: " + body;
        else
            return "��� ������������: " + username + "\n" +
                    "������� ��������: " + rating + " �� 5" + "\n" +
                    "����������� ��������: " + advantages + "\n" +
                    "����������: " + disadvantages + "\n" +
                    "��������: " + body;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public String getAdvantages() {
        return advantages;
    }

    public void setAdvantages(String advantages) {
        this.advantages = advantages;
    }

    public String getDisadvantages() {
        return disadvantages;
    }

    public void setDisadvantages(String disadvantages) {
        this.disadvantages = disadvantages;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getTitleFeedback() {
        return titleFeedback;
    }

    public void setTitleFeedback(String titleFeedback) {
        this.titleFeedback = titleFeedback;
    }
}

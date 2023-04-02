package model;

import java.util.ArrayList;

public class ProductReview {

    private String productName;
    private ArrayList<Feedback> feedbacks;

    public ProductReview() {
        feedbacks = new ArrayList<>();
    }

    public ProductReview(String productName, ArrayList<Feedback> feedbacks) {
        this.productName = productName;
        this.feedbacks = feedbacks;
    }

    @Override
    public String toString() {
        String result = productName + "\n\n";
        for (Feedback feedback : feedbacks)
            result += feedback.toString() + "\n";
        return result;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ArrayList<Feedback> getFeedbacks() {
        return feedbacks;
    }

    public void setFeedbacks(ArrayList<Feedback> feedbacks) {
        this.feedbacks = feedbacks;
    }
}

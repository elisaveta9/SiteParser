package otzovik;

import loader.HtmlLoader;
import model.Feedback;
import model.ProductReview;
import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.Parser;
import parser.ParserSettings;

import java.io.IOException;
import java.util.ArrayList;

public class OtzovikParser implements Parser<ArrayList<ProductReview>> {

    @Override
    public ArrayList<ProductReview> Parse(@NotNull Document document, @NotNull ParserSettings parserSettings) throws IOException {

        ArrayList<ProductReview> list = new ArrayList<>();

        Elements productsEl = document.getElementsByClass("item");

        for (Element productEl : productsEl){
            String urlFeedbacks = "https://otzovik.com" + productEl.getElementsByClass("reviews-counter").attr("href");

            String countFeedbacks = productEl.getElementsByClass("reviews-counter").text();
            int pages = findPages(countFeedbacks);
            parseFeedbacks(urlFeedbacks, pages);
        }

        return list;
    }

    private ArrayList<Feedback> parseFeedbacks(String url, int pages) throws IOException {
        ArrayList<Feedback> list = new ArrayList<>();

        HtmlLoader loader = new HtmlLoader(url + OtzovikSettings.SEPARATOR + OtzovikSettings.PREFIX);
        boolean endPages = false;

        for (int page = 1; page <= pages; ++page){
            Document document = loader.GetSourceByPageId(page);

            Elements feedbacksEl = document.getElementsByClass("review-title");
            for (Element feedbackEl : feedbacksEl){
                String urlFullFeedback = feedbackEl.attr("href");
                //Feedback feedback = parseFullFeedback("https://otzovik.com" + urlFullFeedback);
            }

        }

        return list;
    }

    private Feedback parseFullFeedback(String urlFullFeedback) throws IOException {
        Feedback feedback = new Feedback();
        Document document = Jsoup.connect(urlFullFeedback).get();

        feedback.setUsername(document.getElementsByClass("user-login fit-with-ava url fn").first().text());
        feedback.setTitleFeedback(document.getElementsByAttribute("h1").toString());
        feedback.setAdvantages(document.getElementsByClass("review-plus").toString());
        feedback.setDisadvantages(document.getElementsByClass("review-minus").toString());
        feedback.setBody(document.getElementsByClass("review-body description").toString());
        feedback.setRating(Integer.parseInt(document.getElementsByClass("product-rating tooltip-right").first().attr("title")));

        return feedback;
    }

    private @NotNull int findPages(@NotNull String str){
        int index = str.indexOf(" ");
        str = str.substring(0,index);
        int count = Integer.parseInt(str);
        int pages = count / 20;
        if (count % 20 != 0) pages++;
        return pages;
    }

}
package irecommend;

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
import java.net.Proxy;
import java.util.ArrayList;
import java.util.Random;

public class IrecommendParser implements Parser<ArrayList<ProductReview>> {
    @Override
    public ArrayList<ProductReview> Parse(@NotNull Document document, @NotNull ParserSettings parserSettings) throws IOException, InterruptedException {
        ArrayList<ProductReview> list = new ArrayList<>();

        Elements elements = document.getElementsByClass("title");
        for (Element el : elements){
            String productName = el.text();
            String urlReview = "https://irecommend.ru" + el.select("a").attr("href");
            ProductReview product = new ProductReview(productName,
                    parseFeedbacks(urlReview));
            list.add(product);
        }

        return list;
    }

    private ArrayList<Feedback> parseFeedbacks(String url) throws IOException, InterruptedException {
        ArrayList<Feedback> list = new ArrayList<>();
        Document document = Jsoup.connect(url)
                .get();
        Random random = new Random();
        int time;

        Elements items = document.getElementsByClass("list-comments").first().children();
        for (Element item : items) {
            String urlFeedback = "https://irecommend.ru" +
                    item.getElementsByClass("more").first().attr("href");
            list.add(parseFeedback(urlFeedback));
            time = random.nextInt() % 5000;
            if (time < 0) time *= -1;
            Thread.sleep(time);
        }

        time = random.nextInt() % 5000;
        if (time < 0) time *= -1;
        Thread.sleep(time);
        return list;
    }

    private Feedback parseFeedback(String url) throws IOException {
        Document document = Jsoup.connect(url)
                .get();
        int rating = 0;

        String username = document.getElementsByClass("reviewer").text();
        Elements stars = document.getElementsByClass("starsRating").first().
                getElementsByClass("star");
        for (Element star : stars) {
            Elements state = star.getElementsByClass("on");
            if (!state.isEmpty())
                rating++;
        }
        String title = document.getElementsByClass("reviewTitle").first().text();
        String advantages = "";
        Elements advantagesEl = document.getElementsByClass("plus");
        if (!advantagesEl.isEmpty()) {
            advantagesEl = advantagesEl.first().children().get(1).children();

            for (Element advantageEl : advantagesEl)
                advantages += advantageEl.text() + "; ";
        }
        String disadvantages = "";
        Elements disadvantagesEl = document.getElementsByClass("plus");
        if (!disadvantagesEl.isEmpty()) {
            disadvantagesEl = disadvantagesEl.first().children().get(1).children();
            for (Element disadvantageEl : disadvantagesEl)
                disadvantages += disadvantageEl.text() + "; ";
        }
        String body = "";
        Elements paragraphs = document.getElementsByClass("views-field-teaser reviewText").
                select("p");
        for (Element paragraph : paragraphs) {
            if (!paragraph.text().isEmpty() && !paragraph.text().equals(""))
                body += paragraph.text() + "\n";
        }

        return new Feedback(username, rating, title, advantages, disadvantages, body);
    }
}

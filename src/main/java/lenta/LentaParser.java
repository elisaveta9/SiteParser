package lenta;

import loader.HtmlLoader;
import model.News;
import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.Parser;
import parser.ParserSettings;

import java.io.IOException;
import java.util.ArrayList;

public class LentaParser implements Parser<ArrayList<News>> {
    @Override
    public ArrayList<News> Parse(@NotNull org.jsoup.nodes.Document document, @NotNull ParserSettings parserSettings) throws IOException {
        ArrayList<News> list = new ArrayList<>();

        Elements newsEl = document.getElementsByClass("parts-page__item");
        for (Element newEl : newsEl){
            String urlNew = newEl.getElementsByClass("card-full-news _parts-news").attr("href");
            if (!urlNew.isEmpty() && !urlNew.equals(""))
                list.add(getFullNew(urlNew));
        }

        return list;
    }

    private News getFullNew(@NotNull String urlPage) throws IOException {

        HtmlLoader htmlLoader;
        if (urlPage.startsWith("/"))
            htmlLoader = new HtmlLoader("https://lenta.ru/" + urlPage);
        else htmlLoader = new HtmlLoader(urlPage);
        Document document = htmlLoader.GetSource();
        String date, topic = "", text = "";
        String title = document.title();
        if (title.endsWith("Мослента")) {
            date = document.getElementsByClass("qzByRHub P5lPq1qA").text();
            topic = document.getElementsByClass("jsx-2838600671 rubric").text();
            Elements paragraphs = document.getElementsByClass("jsx-1117196867");
            for (Element paragraph : paragraphs) {
                text += paragraph.text() + "\n";
            }
        }
        else if (title.endsWith("Lenta.ru")) {
            date = document.getElementsByClass("topic-header__item topic-header__time").text();
            topic = document.getElementsByClass("topic-header__item topic-header__rubric").text();
            text = document.getElementsByClass("topic-body__content").text();
        }
        else {
            date = document.getElementsByClass("qzByRHub P5lPq1qA").text();
            Elements tags = document.getElementsByClass("jsx-1534823328 jsx-2050113987 tag");
            for (Element tag : tags) {
                topic += tag.text() + " ";
            }
            Elements paragraphs = document.getElementsByClass("jsx-342317846 vikont");
            for (Element paragraph : paragraphs) {
                text += paragraph.text() + "\n";
            }
        }

        return  new News(title, date, topic, text);
    }
}

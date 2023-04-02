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
        String date, country, text;
        String title = document.title();
        if (title.matches("^* - Мослента$")) {
            date = document.getElementsByClass("_1Lg_CbTX _240YeLMx").text();
            country = "Россия";
            text = document.getElementsByClass("jsx-1665533423 text txkWXQba").text();
        }
        else {
            date = document.getElementsByClass("topic-header__item topic-header__time").text();
            country = document.getElementsByClass("topic-header__item topic-header__rubric").text();
            text = document.getElementsByClass("topic-body__content").text();
        }

        return  new News(title, date, country, text);

    }
}

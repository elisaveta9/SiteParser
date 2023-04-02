package lifeofpix;

import loader.ImageLoader;
import model.Image;
import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import parser.Parser;
import parser.ParserSettings;

import java.io.IOException;
import java.util.ArrayList;

public class LifeofpixParser implements Parser<ArrayList<Image>> {
    @Override
    public ArrayList<Image> Parse(@NotNull Document document, @NotNull ParserSettings parserSettings) throws IOException {
        ArrayList<Image> list = new ArrayList<>();

        Elements elImg = document.select("img");
        for (Element element : elImg){
            String url = element.getElementsByClass("img-low-to-high").attr("data-src");
            if (!url.isEmpty()){
                String strImageName =
                    url.substring( url.lastIndexOf("/") + 1 );
                Image img = new Image(strImageName, url);
                list.add(img);
                ImageLoader.downloadImage(img);
            }
        }

        return list;
    }
}

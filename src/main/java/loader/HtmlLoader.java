package loader;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import parser.ParserSettings;

import java.io.IOException;

public class HtmlLoader {

    static String url;

    public HtmlLoader(@NotNull String url){
        if (url.isEmpty()) {
            throw new NullPointerException();
        }
        this.url = url;
    }

    public static @NotNull Document GetSource()throws IOException {
        if (url.isEmpty()) {
            throw new NullPointerException("—сылка не была установлена");
        }
        return Jsoup.connect(url).get();
    }

    public static @NotNull Document GetSourceByPageId(int id) throws IOException {
        String currentUrl = url.replace("{CurrentId}", Integer.toString(id));
        return Jsoup.connect(currentUrl).get();
    }

}

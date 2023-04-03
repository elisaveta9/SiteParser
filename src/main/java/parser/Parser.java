package parser;

import model.News;
import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public interface Parser<T>  {
    T Parse(@NotNull Document document, @NotNull ParserSettings parserSettings) throws IOException, InterruptedException;

}
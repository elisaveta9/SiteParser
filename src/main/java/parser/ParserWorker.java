package parser;

import loader.HtmlLoader;
import org.jetbrains.annotations.NotNull;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.util.ArrayList;

public class ParserWorker<T> {

    private Parser<T> parser;
    private ParserSettings parserSettings;
    private boolean isActive;
    public HtmlLoader loader;
    public ArrayList<OnNewDataHandler<T>> onNewDataList = new ArrayList<>();
    public ArrayList<Completed> onCompletedList = new ArrayList<>();

    public ParserWorker(@NotNull Parser parser, @NotNull ParserSettings parserSettings){
        this.parser = parser;
        this.parserSettings = parserSettings;
        loader = new HtmlLoader(parserSettings.BASE_URL + parserSettings.SEPARATOR + parserSettings.PREFIX);
    }

    public @NotNull Parser<T> getParser() {
        return parser;
    }

    public void setParser(@NotNull Parser<T> parser) {   this.parser = parser;   }

    public void Start() throws IOException, InterruptedException {
        isActive = true;
        Worker();
    }
    public void Abort() {
        isActive = false;
    }

    private void Worker() throws IOException, InterruptedException {
        for (int i = parserSettings.getStartPoint(); i <= parserSettings.getEndPoint(); i++) {
            if (!isActive) {
                onCompletedList.get(0).OnCompleted(this);
                return;
            }
            Document document = loader.GetSourceByPageId(i);
            T result = parser.Parse(document, this.parserSettings);
            onNewDataList.get(0).OnNewData(this,result);
        }
        onCompletedList.get(0).OnCompleted(this);
        isActive = false;
    }

}

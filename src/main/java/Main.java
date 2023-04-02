import lenta.LentaParser;
import lenta.LentaSettings;
import model.Image;
import model.News;
import model.ProductReview;
import newData.NewDataImage;
import newData.NewDataNews;
import newData.NewDataProductReview;
import otzovik.OtzovikParser;
import otzovik.OtzovikSettings;
import parser.Completed;
import parser.ParserWorker;
import wallpaper_abyss.WallpaperParser;
import wallpaper_abyss.WallpaperSettings;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] arg){

        /*ParserWorker<ArrayList<ProductReview>> parser = new ParserWorker<>(new OtzovikParser(), new OtzovikSettings(1, 3));

        parser.onCompletedList.add(new Completed());
        parser.onNewDataList.add(new NewDataProductReview());

        try {
            parser.Start();
            parser.Abort();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/

        /*ParserWorker<ArrayList<News>> parserWorker = new ParserWorker<>(new LentaParser(), new LentaSettings(3, 10));

        parserWorker.onCompletedList.add(new Completed());
        parserWorker.onNewDataList.add(new NewDataNews());

        try {
            parserWorker.Start();
            parserWorker.Abort();
        }catch (IOException e){
            throw new RuntimeException(e);
        }*/

        ParserWorker<ArrayList<Image>> parserWorker= new ParserWorker<>(new WallpaperParser(), new WallpaperSettings(1,5));

        parserWorker.onNewDataList.add(new NewDataImage());
        parserWorker.onCompletedList.add(new Completed());

        try {
            parserWorker.Start();
            parserWorker.Abort();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

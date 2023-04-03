import irecommend.IrecommendParser;
import irecommend.IrecommendSettings;
import lenta.LentaParser;
import lenta.LentaSettings;
import lifeofpix.LifeofpixParser;
import lifeofpix.LifeofpixSettings;
import model.Image;
import model.News;
import model.ProductReview;
import newData.NewDataImage;
import newData.NewDataNews;
import newData.NewDataProductReview;
import parser.Completed;
import parser.ParserWorker;

import java.io.IOException;
import java.util.ArrayList;

public class Main {

    public static void main(String[] arg){

        ParserWorker<ArrayList<ProductReview>> parser = new ParserWorker<>(new IrecommendParser(),
                new IrecommendSettings(1, 3));

        parser.onCompletedList.add(new Completed());
        parser.onNewDataList.add(new NewDataProductReview());

        try {
            parser.Start();
            parser.Abort();
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        /*ParserWorker<ArrayList<News>> parserWorker = new ParserWorker<>(new LentaParser(), new LentaSettings(1, 5));

        parserWorker.onCompletedList.add(new Completed());
        parserWorker.onNewDataList.add(new NewDataNews());

        try {
            parserWorker.Start();
            parserWorker.Abort();
        }catch (IOException e){
            throw new RuntimeException(e);
        }*/

        /*ParserWorker<ArrayList<Image>> parserWorker= new ParserWorker<>(new LifeofpixParser(), new LifeofpixSettings(1,15));

        parserWorker.onNewDataList.add(new NewDataImage());
        parserWorker.onCompletedList.add(new Completed());

        try {
            parserWorker.Start();
            parserWorker.Abort();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }*/
    }
}

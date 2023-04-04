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
import java.util.Scanner;

public class Main {

    public static void main(String[] arg){

        Scanner in = new Scanner(System.in);
        System.out.println("Парсить:\n" +
                "1. Сайт новостей lenta\n" +
                "2. Сайт с рецензиями на товары irecommend\n" +
                "3. Сайт с изображениями lifeofpix");
        int site = in.nextInt();
        if (site < 1 || site > 3) { System.out.println("Некорректное значение"); return; }

        System.out.println("Начать с страницы");
        int begin = in.nextInt();
        System.out.println("Закончить на странице");
        int end = in.nextInt();

        if (begin < 1 || begin > end) { System.out.println("Некорректное значение"); return; }

        if (site == 1){
            ParserWorker<ArrayList<News>> parserWorker = new ParserWorker<>(new LentaParser(),
                    new LentaSettings(begin, end));

            parserWorker.onCompletedList.add(new Completed());
            parserWorker.onNewDataList.add(new NewDataNews());

            try {
                parserWorker.Start();
                parserWorker.Abort();
                return;
            }catch (IOException | InterruptedException e){
                throw new RuntimeException(e);
            }
        }
        if (site == 2) {
            ParserWorker<ArrayList<ProductReview>> parser = new ParserWorker<>(new IrecommendParser(),
                    new IrecommendSettings(begin - 1, end - 1));

            parser.onCompletedList.add(new Completed());
            parser.onNewDataList.add(new NewDataProductReview());

            try {
                parser.Start();
                parser.Abort();
                return;
            } catch (IOException e) {
                throw new RuntimeException(e);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        if (site == 3){
            ParserWorker<ArrayList<Image>> parserWorker= new ParserWorker<>(new LifeofpixParser(),
                    new LifeofpixSettings(begin, end));

            parserWorker.onNewDataList.add(new NewDataImage());
            parserWorker.onCompletedList.add(new Completed());

            try {
                parserWorker.Start();
                parserWorker.Abort();
            } catch (IOException | InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}

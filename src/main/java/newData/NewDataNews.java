package newData;

import model.News;
import org.jetbrains.annotations.NotNull;
import parser.OnNewDataHandler;

import java.util.ArrayList;

public class NewDataNews implements OnNewDataHandler<ArrayList<News>> {
    @Override
    public void OnNewData(@NotNull Object sender, @NotNull ArrayList<News> e) {
        for (News news : e)
            System.out.println(news + "\n");
    }
}

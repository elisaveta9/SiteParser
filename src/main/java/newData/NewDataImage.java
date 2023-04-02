package newData;

import model.Image;
import model.ProductReview;
import parser.OnNewDataHandler;

import java.util.ArrayList;

public class NewDataImage implements OnNewDataHandler<ArrayList<Image>> {

    @Override
    public void OnNewData(Object sender, ArrayList<Image> e) {
        for (Image image : e)
            System.out.println(image);
    }
}
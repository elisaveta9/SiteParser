package newData;

import model.ProductReview;
import org.jetbrains.annotations.NotNull;
import parser.OnNewDataHandler;

import java.util.ArrayList;

public class NewDataProductReview implements OnNewDataHandler<ArrayList<ProductReview>> {

    @Override
    public void OnNewData(Object sender, ArrayList<ProductReview> e) {
        for (ProductReview productReview : e)
            System.out.println(productReview);
    }
}

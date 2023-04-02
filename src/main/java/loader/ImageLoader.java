package loader;

import model.Image;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;

public class ImageLoader {

    private static String IMAGE_DESTINATION_FOLDER = "D:/java/LandscapeWallpaper";

    public ImageLoader(){

    }

    public ImageLoader(String newDirectory){
        IMAGE_DESTINATION_FOLDER = newDirectory;
    }

    public static void downloadImage(Image image){

        try {

            //open the stream from URL
            URL urlImage = new URL(image.getUrl());
            InputStream in = urlImage.openStream();

            byte[] buffer = new byte[4096];
            int n = -1;

            OutputStream os =
                    new FileOutputStream( IMAGE_DESTINATION_FOLDER + "/" + image.getName() );

            //write bytes to the output stream
            while ( (n = in.read(buffer)) != -1 ){
                os.write(buffer, 0, n);
            }

            //close the stream
            os.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package lifeofpix;

import parser.ParserSettings;

public class LifeofpixSettings extends ParserSettings {
    public LifeofpixSettings(int start,int end){
        startPoint = start;
        endPoint = end;
        BASE_URL = "https://www.lifeofpix.com/gallery/nature-2";
        SEPARATOR = "/";
        PREFIX = "{CurrentId}";
    }
}

package wallpaper_abyss;

import parser.ParserSettings;

public class WallpaperSettings extends ParserSettings {
    public WallpaperSettings(int start,int end){
        startPoint = start;
        endPoint = end;
        BASE_URL = "https://wall.alphacoders.com/search.php?search=landscape";
        SEPARATOR = "&";
        PREFIX = "page={CurrentId}";
    }
}

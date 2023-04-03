package irecommend;

import parser.ParserSettings;

public class IrecommendSettings extends ParserSettings {

    public IrecommendSettings(int start, int end) {
        startPoint = start;
        endPoint = end;
        BASE_URL = "https://irecommend.ru/catalog/list/7681-203835";
        SEPARATOR = "?";
        PREFIX = "page={CurrentId}/";
    }

}

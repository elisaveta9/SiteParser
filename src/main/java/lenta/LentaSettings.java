package lenta;

import parser.ParserSettings;

public class LentaSettings extends ParserSettings{

    public LentaSettings(int start, int end) {
        startPoint = start;
        endPoint = end;
        BASE_URL="https://lenta.ru/parts/news";
        SEPARATOR = "/";
        PREFIX="{CurrentId}/";
    }
}

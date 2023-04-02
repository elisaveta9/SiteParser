package otzovik;

import parser.ParserSettings;

public class OtzovikSettings extends ParserSettings {

    public OtzovikSettings(int start, int end) {
        startPoint = start;
        endPoint = end;
        BASE_URL = "https://otzovik.com/internet/web-services_online/internet_online_other";
        SEPARATOR = "/";
        PREFIX = "{CurrentId}/";
    }
}

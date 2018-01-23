package ua.training.util.text;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;

public class Localization {
    public static final Map<String, Locale> localeMap = initLocales();

    private static ResourceBundle bundle = ResourceBundle.getBundle("i18n/alerts", new Locale("en", "US"));

    public static void setBundle(Locale locale) {
        bundle = ResourceBundle.getBundle("i18n/alerts", locale);
    }

    public static String getMessage(String message) {
        return bundle.getString(message);
    }

    private static Map<String, Locale> initLocales() {
        Map<String, Locale> localeMap = new HashMap<>();
        localeMap.put("en_US", new Locale("en", "US"));
        localeMap.put("ru_RU", new Locale("ru", "RU"));
        return localeMap;
    }
}

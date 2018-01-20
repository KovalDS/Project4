package ua.training.controller.util;

//TODO remove somewhere

import ua.training.model.entity.Article;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Util {
    public  static <T> List<T> listIntersection(List<T> list1, List<T> list2) {
        return list1.stream()
                .filter(list2::contains)
                .collect(Collectors.toList());
    }

    public static String parseStringToHtml(String text) {
        StringBuffer buffer = new StringBuffer("");

        String[] paragraphs = text.split("\r\n");
        for (String paragraph : paragraphs) {
            buffer = buffer.append("<p>")
                            .append(paragraph)
                            .append("</p>");
        }
        return buffer.toString();
    }

    public static boolean priceIsValid(String price) {
        return Pattern.matches("^[0-9]*[,.][0-9]{2}$", price);
    }

    public static Map<Integer, List<Article>> divideOnPages(List<Article> articles) { //TODO must do something with this
        Map<Integer, List<Article>> result = new HashMap<>();

        for (int i = 0; i < articles.size()/6; i++) {
            result.put(i+1, articles.subList(i*6, (i+1)*6));
        }
        if (!articles.subList((articles.size()/6)*6, articles.size()).isEmpty()) {
            result.put(articles.size()/6 + 1, articles.subList((articles.size()/6)*6, articles.size()));
        }

        return result;
    }
}

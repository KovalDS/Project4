package ua.training.controller.util;

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

    public static boolean emailIsValid(String email) {
        return Pattern.matches("^[a-zA-Z0-9_-]+(\\.[a-z0-9_-]+)*@[a-z]+\\.[a-z]{2,6}$", email);
    }

    public static boolean passwordIsValid(String password) {
        return Pattern.matches("^[a-zA-Z0-9!@#$%^&*()?><+_=\"\',-{}]{8,}$",password);
    }
}

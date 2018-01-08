package ua.training.controller;

//TODO remove somewhere

import java.util.List;
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
}

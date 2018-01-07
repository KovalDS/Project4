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


}

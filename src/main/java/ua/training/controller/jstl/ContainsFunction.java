package ua.training.controller.jstl;

import ua.training.model.entity.Periodical;

import java.util.List;

public class ContainsFunction {
    public static boolean contains(List<Periodical> periodicals, Periodical periodical) {
        return periodicals.contains(periodical);
    }
}

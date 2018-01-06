package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class AddToBasketCommand implements Command {
    private PeriodicalService periodicalService;

    public AddToBasketCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int periodicalId = Integer.parseInt(req.getParameter("periodical_id"));
        Periodical periodical = periodicalService.getPeriodicalById(periodicalId);
        Set<Periodical> basket = (Set<Periodical>) req.getSession().getAttribute("basket");

        if (basket == null) {
            basket = new HashSet<>();
        }

        basket.add(periodical);
        req.getSession().setAttribute("basket", basket);
        return new DefaultCommand(periodicalService).execute(req, resp);
    }
}

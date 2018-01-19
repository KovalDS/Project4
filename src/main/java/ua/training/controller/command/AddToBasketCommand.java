package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class AddToBasketCommand implements Command {
    private PeriodicalService periodicalService; //TODO order service??

    public AddToBasketCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int basketSize;
        int totalPrice = 0;

        int periodicalId = Integer.parseInt(req.getParameter("periodical_id"));
        Periodical periodical = periodicalService.getPeriodicalById(periodicalId);
        Set<Periodical> basket = (Set<Periodical>) req.getSession().getAttribute("basket");

        if (basket == null) {
            basket = new HashSet<>();
        }

        basket.add(periodical);
        basketSize = basket.size();
        for (Periodical item : basket) {
            totalPrice += item.getPrice();
        }

        req.getSession().setAttribute("basket_badge", "<span class=\"badge progress-bar-danger\" >" + basketSize + "</span>");
        req.getSession().setAttribute("total_basket_price", totalPrice);
        req.getSession().setAttribute("basket", basket);
        return "/home";
    }
}

package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalService;
import ua.training.util.text.constants.Attributes;
import ua.training.util.text.constants.Messages;
import ua.training.util.text.constants.Parameteres;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashSet;
import java.util.Set;

public class AddToBasketCommand implements Command {
    private PeriodicalService periodicalService;

    public AddToBasketCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int basketSize;
        int totalPrice = 0;

        int periodicalId = Integer.parseInt(req.getParameter(Parameteres.PERIODICAL_ID));
        Periodical periodical = periodicalService.getPeriodicalById(periodicalId);
        Set<Periodical> basket = (Set<Periodical>) req.getSession().getAttribute(Attributes.BASKET);

        if (basket == null) {
            basket = new HashSet<>();
        }

        basket.add(periodical);
        basketSize = basket.size();
        for (Periodical item : basket) {
            totalPrice += item.getPrice();
        }

        req.getSession().setAttribute(Attributes.BASKET_BADGE, Messages.BADGE_OPEN_TAG +
                                                                    basketSize + Messages.BADGE_CLOSE_TAG);
        req.getSession().setAttribute(Attributes.BASKET_PRICE, totalPrice);
        req.getSession().setAttribute(Attributes.BASKET, basket);
        return (String) req.getSession().getAttribute(Attributes.PREVIOUS_PAGE);
    }
}

package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalService;
import ua.training.util.constants.Attributes;
import ua.training.util.constants.Messages;
import ua.training.util.constants.Parameteres;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class DeleteFromBasket implements Command {
    private PeriodicalService periodicalService;

    public DeleteFromBasket(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int basketSize;
        int totalPrice = 0;
        Set<Periodical> basket = (Set<Periodical>) req.getSession().getAttribute(Attributes.BASKET);
        int periodicalId = Integer.parseInt(req.getParameter(Parameteres.PERIODICAL_ID));
        Periodical periodical = periodicalService.getPeriodicalById(periodicalId);

        basket.remove(periodical);
        basketSize = basket.size();

        if (basketSize == 0) {
            req.getSession().removeAttribute(Attributes.BASKET_BADGE);
            req.getSession().removeAttribute(Attributes.BASKET_PRICE);
            req.getSession().removeAttribute(Attributes.BASKET);
            return (String) req.getSession().getAttribute(Attributes.PREVIOUS_PAGE);
        }

        for (Periodical item : basket) {
            totalPrice += item.getPrice();
        }

        req.getSession().setAttribute(Attributes.BASKET_PRICE, totalPrice);
        req.getSession().setAttribute(Attributes.BASKET_BADGE, Messages.BADGE_OPEN_TAG
                                                                            + basketSize + Messages.BADGE_CLOSE_TAG);

        req.setAttribute(Attributes.DROPDOWN_OPEN, Messages.DROPDOWN_OPEN);

        return (String) req.getSession().getAttribute(Attributes.PREVIOUS_PAGE);
    }
}

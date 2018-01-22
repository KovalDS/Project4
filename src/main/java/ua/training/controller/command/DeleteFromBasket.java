package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalService;
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
        Set<Periodical> basket = (Set<Periodical>) req.getSession().getAttribute("basket");
        int periodicalId = Integer.parseInt(req.getParameter(Parameteres.PERIODICAL_ID));
        Periodical periodical = periodicalService.getPeriodicalById(periodicalId);

        basket.remove(periodical);
        basketSize = basket.size();

        if (basketSize == 0) {
            req.getSession().removeAttribute("basket_badge");
            req.getSession().removeAttribute("total_basket_price");
            req.getSession().removeAttribute("basket");
            return (String) req.getSession().getAttribute("previous_page");
        }

        for (Periodical item : basket) {
            totalPrice += item.getPrice();
        }

        req.getSession().setAttribute("total_basket_price", totalPrice);
        req.getSession().setAttribute("basket_badge", "<span class=\"badge progress-bar-danger\" style = \"${requestScope.display_basket_size}\">" + basketSize + "</span>");

        req.setAttribute("dropdown_open", "open");
        req.getSession().setAttribute("basket_size", basketSize);

        return (String) req.getSession().getAttribute("previous_page");
    }
}

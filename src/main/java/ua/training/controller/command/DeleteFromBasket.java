package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Set;

public class DeleteFromBasket implements Command {
    PeriodicalService periodicalService;

    public DeleteFromBasket(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int basketSize;
        int totalPrice = 0;
        Set<Periodical> basket = (Set<Periodical>) req.getSession().getAttribute("basket");
        int periodicalId = Integer.parseInt(req.getParameter("periodical_id"));
        Periodical periodical = periodicalService.getPeriodicalById(periodicalId);

        basket.remove(periodical);
        basketSize = basket.size();

        if (basketSize == 0) {
            req.getSession().removeAttribute("basket_badge");
            req.getSession().removeAttribute("total_basket_price");
            req.getSession().removeAttribute("basket");
            return new ShowPeriodicalsList(new PeriodicalService()).execute(req, resp);
        }

        for (Periodical item : basket) {
            totalPrice += item.getPrice();
        }

        req.getSession().setAttribute("total_basket_price", totalPrice);
        req.getSession().setAttribute("basket_badge", "<span class=\"badge progress-bar-danger\" style = \"${requestScope.display_basket_size}\">" + basketSize + "</span>");

        req.setAttribute("dropdown_open", "open");
        req.getSession().setAttribute("basket_size", basketSize);

        return new ShowPeriodicalsList(new PeriodicalService()).execute(req, resp);
    }
}

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
        Set<Periodical> basket = (Set<Periodical>) req.getSession().getAttribute("basket");
        int periodicalId = Integer.parseInt(req.getParameter("periodical_id"));
        Periodical periodical = periodicalService.getPeriodicalById(periodicalId);

        basket.remove(periodical);

        return new DefaultCommand(new PeriodicalService()).execute(req, resp);
    }
}

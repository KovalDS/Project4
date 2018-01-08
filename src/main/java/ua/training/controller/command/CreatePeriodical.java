package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//TODO negative price!!!!!
public class CreatePeriodical implements Command{
    PeriodicalService periodicalService;

    public CreatePeriodical(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int price;

        String name = req.getParameter("periodical_name");
        String description = req.getParameter("periodical_description");
        String priceStr = req.getParameter("periodical_price");

        priceStr = priceStr.replaceAll("[,.]", "");
        price = Integer.parseInt(priceStr);

        if (price <= 0) {  //TODO create input validation method
            return "/WEB-INF/view/add_periodical.jsp";
        }

        periodicalService.createPeriodical(new Periodical.PeriodicalBuilder()
                                                .buildName(name)
                                                .buildDescription(description)
                                                .buildPrice(price)
                                                .buildPeriodical());

        return "/WEB-INF/view/add_periodical.jsp";
    }
}

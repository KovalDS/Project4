package ua.training.controller.command;

import ua.training.controller.util.Util;
import ua.training.model.entity.Periodical;
import ua.training.model.service.AdminService;
import ua.training.model.service.PeriodicalService;
import ua.training.util.constants.Attributes;
import ua.training.util.constants.Pages;
import ua.training.util.constants.Parameteres;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Pattern;

public class CreatePeriodical implements Command{
    private AdminService adminService;

    public CreatePeriodical(AdminService adminService) {
        this.adminService = adminService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int price;

        String name = req.getParameter(Parameteres.PERIODICAL_NAME);
        String description = req.getParameter(Parameteres.PERIODICAL_DESCRIPTION);
        String priceStr = req.getParameter(Parameteres.PERIODICAL_PRICE);

        if (!Util.priceIsValid(priceStr)) {
            req.setAttribute(Attributes.MESSAGE, "<div class=\"alert alert-danger\">Please, input price in format X.XX</div>");
            return Pages.ADD_PERIODICAL;
        }

        priceStr = priceStr.replaceAll("[,.]", "");
        price = Integer.parseInt(priceStr);

        adminService.createPeriodical(new Periodical.PeriodicalBuilder()
                                                .buildName(name)
                                                .buildDescription(description)
                                                .buildPrice(price)
                                                .buildPeriodical());

        return Pages.ADD_PERIODICAL;
    }
}

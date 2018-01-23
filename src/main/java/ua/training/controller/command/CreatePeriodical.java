package ua.training.controller.command;

import ua.training.controller.util.Util;
import ua.training.model.entity.Periodical;
import ua.training.model.service.AdminService;
import ua.training.util.text.Localization;
import ua.training.util.text.constants.Attributes;
import ua.training.util.text.constants.Messages;
import ua.training.util.text.constants.Pages;
import ua.training.util.text.constants.Parameteres;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
            req.setAttribute(Attributes.MESSAGE, Localization.getMessage(Messages.WRONG_PRICE_FORMAT_MESSAGE));
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

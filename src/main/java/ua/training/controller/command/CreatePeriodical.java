package ua.training.controller.command;

import ua.training.controller.util.Util;
import ua.training.model.entity.Periodical;
import ua.training.model.service.AdminService;
import ua.training.model.service.PeriodicalService;

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

        String name = req.getParameter("periodical_name");
        String description = req.getParameter("periodical_description");
        String priceStr = req.getParameter("periodical_price");

        if (!Util.priceIsValid(priceStr)) {  //TODO error message
            return "/WEB-INF/view/add_periodical.jsp";
        }

        priceStr = priceStr.replaceAll("[,.]", "");
        price = Integer.parseInt(priceStr);

        adminService.createPeriodical(new Periodical.PeriodicalBuilder()
                                                .buildName(name)
                                                .buildDescription(description)
                                                .buildPrice(price)
                                                .buildPeriodical());

        return "/WEB-INF/view/add_periodical.jsp";
    }
}

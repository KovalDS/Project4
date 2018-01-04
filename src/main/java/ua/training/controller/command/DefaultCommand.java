package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class DefaultCommand implements Command {
    PeriodicalService periodicalService;

    public DefaultCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = "/WEB-INF/view/periodicals.jsp";

        List<Periodical> periodicalList = periodicalService.getAllPeriodicals();
        req.getSession().setAttribute("periodical_list", periodicalList);

        User user =  (User) req.getSession().getAttribute("user");
        Role role = (Role) req.getSession().getAttribute("user_role");

        if (user != null && !role.getName().equals("admin")) {
            List<Periodical> purchasedPeriodicals = periodicalService.getPeriodicalsOfUser(user.getId());
            req.setAttribute("available_periodicals", purchasedPeriodicals);
        } else if (role.getName().equals("admin")) {
            req.setAttribute("available_periodicals", periodicalList);
        } else {
            req.setAttribute("available_periodicals", new ArrayList());
        }

        return page;
    }
}

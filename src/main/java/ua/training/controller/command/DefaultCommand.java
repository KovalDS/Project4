package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DefaultCommand implements Command {
    private PeriodicalService periodicalService;

    public DefaultCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = "/WEB-INF/view/periodicals.jsp";

        List<Periodical> periodicalList = periodicalService.getAllPeriodicals();
        req.setAttribute("periodical_list", periodicalList);

        User user =  (User) req.getSession().getAttribute("user");
        Role role = (Role) req.getSession().getAttribute("user_role");

        if (user != null && !role.getName().equals("admin")) {  //TODO refactor using map
            List<Periodical> purchasedPeriodicals = periodicalService.getPeriodicalsOfUser(user.getId()); //TODO if available periodicals will be stored in session, you can avoid calling default command in other commands
            req.setAttribute("available_periodicals", purchasedPeriodicals);
        } else if (role != null && role.getName().equals("admin")) {
            req.setAttribute("available_periodicals", periodicalList);
        } else {
            req.setAttribute("available_periodicals", new ArrayList());
        }

        return page;
    }
}

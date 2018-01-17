package ua.training.controller.command;

import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowPeriodicalsList implements Command {
    private PeriodicalService periodicalService;

    public ShowPeriodicalsList(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String page = "/WEB-INF/view/periodicals.jsp";

        Map<Integer, List<Periodical>> periodicalsDividedOnPages = periodicalService.getPeriodicalsDividedOnPages(4);
        String periodicalPageStr = req.getParameter("periodicals_page");
        if (periodicalPageStr == null) {
            periodicalPageStr = "1";
        }
        List<Periodical> periodicalList = periodicalsDividedOnPages.getOrDefault(Integer.parseInt(periodicalPageStr), periodicalsDividedOnPages.get(1));
        req.setAttribute("pages", periodicalsDividedOnPages.keySet());
        req.setAttribute("current_page", Integer.parseInt(periodicalPageStr));
        req.setAttribute("periodical_list", periodicalList);

        User user =  (User) req.getSession().getAttribute("user");
        Role role = (Role) req.getSession().getAttribute("user_role");

        if (user != null && !role.equals(Role.ADMIN)) {  //TODO refactor using map
            List<Periodical> purchasedPeriodicals = periodicalService.getPeriodicalsOfUser(user.getId()); //TODO if available periodicals will be stored in session, you can avoid calling default command in other commands
            req.setAttribute("available_periodicals", purchasedPeriodicals);

        } else if (role != null && role.equals(Role.ADMIN)) {
            req.setAttribute("available_periodicals", periodicalList);
        } else {
            req.setAttribute("available_periodicals", new ArrayList());
        }

        return page;
    }
}

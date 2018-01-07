package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.PeriodicalService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;

public class ShowArticlesListCommand implements Command {
    private PeriodicalService periodicalService;

    public ShowArticlesListCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int periodicalId = Integer.parseInt(req.getParameter("periodical_id"));
        Periodical periodical;
        User user = (User) req.getSession().getAttribute("user");
        List<Periodical> purchasedPeriodicals;

        if (user.getRoles().contains(new Role.RoleBuilder()
                                                .buildName("admin")
                                                .buildRole())) {

            purchasedPeriodicals = periodicalService.getAllPeriodicals();
        } else {
            purchasedPeriodicals = periodicalService.getPeriodicalsOfUser(user.getId());
        }

        periodical = periodicalService.getPeriodicalById(periodicalId);

        if (!purchasedPeriodicals.contains(periodical)) {
            //TODO error page here
            return null;
        }

        req.setAttribute("articles", periodical.getArticles());
        req.setAttribute("periodical", periodical);

        return "/WEB-INF/view/articles.jsp";
    }
}


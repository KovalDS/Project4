package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.PeriodicalService;
import ua.training.model.service.strategy.StrategyFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MySubscriptionsCommand implements Command {
    PeriodicalService periodicalService;

    public MySubscriptionsCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Periodical> periodicals;
        User user = (User) req.getSession().getAttribute("user");
        Role role = (Role) req.getSession().getAttribute("user_role");

        periodicalService.setStrategy(StrategyFactory.getStrategy(role));
        periodicals = periodicalService.getPeriodicalsOfUser(user.getId());

        req.setAttribute("periodical_list", periodicals);
        req.setAttribute("available_periodicals", periodicals);

        req.getSession().setAttribute("previous_page", "/my_subscriptions");

        return "/WEB-INF/view/periodicals.jsp";
    }
}

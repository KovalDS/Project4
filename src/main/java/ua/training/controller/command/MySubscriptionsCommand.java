package ua.training.controller.command;

import ua.training.model.entity.Periodical;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.PeriodicalService;
import ua.training.model.service.strategy.StrategyFactory;
import ua.training.util.constants.Attributes;
import ua.training.util.constants.Commands;
import ua.training.util.constants.Pages;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class MySubscriptionsCommand implements Command {
    private PeriodicalService periodicalService;

    public MySubscriptionsCommand(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Periodical> periodicals;
        User user = (User) req.getSession().getAttribute(Attributes.USER);
        Role role = (Role) req.getSession().getAttribute(Attributes.USER_ROLE);

        periodicalService.setStrategy(StrategyFactory.getStrategy(role));
        periodicals = periodicalService.getPeriodicalsOfUser(user.getId());

        req.setAttribute(Attributes.PERIODICAL_LIST, periodicals);
        req.setAttribute(Attributes.AVAILABLE_PERIODICALS, periodicals);

        req.getSession().setAttribute(Attributes.PREVIOUS_PAGE, Commands.MY_SUBSCRIPTIONS);

        return Pages.PERIODICALS;
    }
}

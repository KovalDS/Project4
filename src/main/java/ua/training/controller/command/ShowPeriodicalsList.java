package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.PeriodicalService;
import ua.training.model.service.strategy.StrategyFactory;
import ua.training.util.constants.Attributes;
import ua.training.util.constants.Pages;
import ua.training.util.constants.Parameteres;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowPeriodicalsList implements Command {
    private PeriodicalService periodicalService;
    private static final Logger logger = LogManager.getLogger(ShowPeriodicalsList.class);

    public ShowPeriodicalsList(PeriodicalService periodicalService) {
        this.periodicalService = periodicalService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        List<Periodical> availablePeriodicals;

        Map<Integer, List<Periodical>> periodicalsDividedOnPages = periodicalService
                .getPeriodicalsDividedOnPages(4);

        String periodicalPageStr = req.getParameter(Parameteres.PERIODICALS_PAGE);

        if (periodicalPageStr == null || periodicalPageStr.isEmpty()) {
            periodicalPageStr = "1";
        }

        List<Periodical> periodicalList = periodicalsDividedOnPages.getOrDefault(Integer.parseInt(periodicalPageStr),
                periodicalsDividedOnPages.get(1));

        req.setAttribute(Attributes.PAGES, periodicalsDividedOnPages.keySet());
        req.setAttribute(Attributes.CURRENT_PAGE, Integer.parseInt(periodicalPageStr));
        req.setAttribute(Attributes.PERIODICAL_LIST, periodicalList);

        User user =  (User) req.getSession().getAttribute(Attributes.USER);
        Role role = (Role) req.getSession().getAttribute(Attributes.USER_ROLE);

        if (user != null) {
            periodicalService.setStrategy(StrategyFactory.getStrategy(role));
            availablePeriodicals = periodicalService.getPeriodicalsOfUser(user.getId());
        } else {
            availablePeriodicals = new ArrayList<>();
        }
        req.setAttribute(Attributes.AVAILABLE_PERIODICALS, availablePeriodicals);

        req.getSession().setAttribute(Attributes.PREVIOUS_PAGE, "/home?periodicals_page=" + periodicalPageStr);

        return Pages.PERIODICALS;
    }
}

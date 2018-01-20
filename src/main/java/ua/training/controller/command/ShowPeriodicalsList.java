package ua.training.controller.command;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.PeriodicalService;
import ua.training.model.service.strategy.StrategyFactory;

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
        String page = "/WEB-INF/view/periodicals.jsp";
        List<Periodical> availablePeriodicals;

        Map<Integer, List<Periodical>> periodicalsDividedOnPages = periodicalService.getPeriodicalsDividedOnPages(4);
        String periodicalPageStr = req.getParameter("periodicals_page");

        if (periodicalPageStr == null || periodicalPageStr.isEmpty()) {
            periodicalPageStr = "1";
        }

        List<Periodical> periodicalList = periodicalsDividedOnPages.getOrDefault(Integer.parseInt(periodicalPageStr), periodicalsDividedOnPages.get(1));
        req.setAttribute("pages", periodicalsDividedOnPages.keySet());
        req.setAttribute("current_page", Integer.parseInt(periodicalPageStr));
        req.setAttribute("periodical_list", periodicalList);

        User user =  (User) req.getSession().getAttribute("user");
        Role role = (Role) req.getSession().getAttribute("user_role");

        if (user != null) {
            periodicalService.setStrategy(StrategyFactory.getStrategy(role));
            availablePeriodicals = periodicalService.getPeriodicalsOfUser(user.getId());
        } else {
            availablePeriodicals = new ArrayList<>();
        }
        req.setAttribute("available_periodicals", availablePeriodicals);

        req.getSession().setAttribute("previous_page", "/home?periodicals_page=" + periodicalPageStr);

        return page;
    }
}

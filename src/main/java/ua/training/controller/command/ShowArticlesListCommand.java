package ua.training.controller.command;

import ua.training.controller.util.Util;
import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.PeriodicalService;
import ua.training.model.service.strategy.StrategyFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

        periodicalService.setStrategy(StrategyFactory.getStrategy(user.getRole()));
        purchasedPeriodicals = periodicalService.getPeriodicalsOfUser(user.getId());

        periodical = periodicalService.getPeriodicalById(periodicalId);

        if (!purchasedPeriodicals.contains(periodical)) {
            //TODO error page here
            return null;
        }

        List<Article> articles = periodical.getArticles();
        Map<Integer, List<Article>> articlePages = Util.divideOnPages(articles);

        String page = req.getParameter("articles_page");
        if (page == null) {
            page = "1";
        }
        req.setAttribute("pages", articlePages.keySet());
        req.setAttribute("current_page", page);


        req.setAttribute("articles", articlePages.getOrDefault(Integer.parseInt(page), articlePages.get(1)));
        req.setAttribute("periodical", periodical);

        return "/WEB-INF/view/articles.jsp";
    }
}


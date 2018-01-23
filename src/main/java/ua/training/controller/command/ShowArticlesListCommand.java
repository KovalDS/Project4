package ua.training.controller.command;

import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.User;
import ua.training.model.service.ArticleService;
import ua.training.model.service.strategy.StrategyFactory;
import ua.training.util.text.constants.Attributes;
import ua.training.util.text.constants.Commands;
import ua.training.util.text.constants.Pages;
import ua.training.util.text.constants.Parameteres;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ShowArticlesListCommand implements Command {
    private ArticleService articleService;

    public ShowArticlesListCommand(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        Periodical periodical;
        List<Article> purchasedArticles;
        Map<Integer, List<Article>> articlesOfPeriodical;
        List<Article> pageOfArticles;

        int periodicalId = Integer.parseInt(req.getParameter(Parameteres.PERIODICAL_ID));
        periodical = articleService.getPeriodicalById(periodicalId);
        User user = (User) req.getSession().getAttribute(Attributes.USER);

        articleService.setStrategy(StrategyFactory.getStrategy(user.getRole()));
        purchasedArticles = articleService.getArticlesOfUser(user.getId());

        articlesOfPeriodical = articleService.getArticlesOfPeriodicalDividedOnPages(periodicalId, 6);

        String page = req.getParameter(Parameteres.ARTICLES_PAGE);
        if (page == null || page.isEmpty()) {
            page = "1";
        }

        pageOfArticles = articlesOfPeriodical.getOrDefault(Integer.parseInt(page), new ArrayList<>());

        if (!purchasedArticles.containsAll(pageOfArticles)) { //FIXME user without subscription can access page if periodical has no articles
            return Pages.ERROR_403;
        }
        req.setAttribute(Attributes.PAGES, articlesOfPeriodical.keySet());
        req.setAttribute(Attributes.CURRENT_PAGE, page);

        req.setAttribute(Attributes.ARTICLES, pageOfArticles);
        req.setAttribute(Attributes.PERIODICAl, periodical);

        req.getSession().setAttribute(Attributes.PREVIOUS_PAGE, Commands.PERIODICAL + "?" + Parameteres.PERIODICAL_ID
                                                + "=" + periodicalId + "&" + Parameteres.ARTICLES_PAGE + "=" + page);

        return Pages.ARTICLES;
    }
}


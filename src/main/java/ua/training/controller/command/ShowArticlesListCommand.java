package ua.training.controller.command;

import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.User;
import ua.training.model.exception.PeriodicalNotFoundException;
import ua.training.model.service.ArticleService;
import ua.training.model.service.strategy.StrategyFactory;
import ua.training.util.constants.Parameteres;

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
        User user = (User) req.getSession().getAttribute("user");

        articleService.setStrategy(StrategyFactory.getStrategy(user.getRole()));
        purchasedArticles = articleService.getArticlesOfUser(user.getId());

        articlesOfPeriodical = articleService.getArticlesOfPeriodicalDividedOnPages(periodicalId, 6);

        String page = req.getParameter(Parameteres.ARTICLES_PAGE);
        if (page == null || page.isEmpty()) {
            page = "1";
        }

        pageOfArticles = articlesOfPeriodical.getOrDefault(Integer.parseInt(page), new ArrayList<>());

        if (!purchasedArticles.containsAll(pageOfArticles)) { //FIXME user without subscription can access page if periodical has no articles
            return "/WEB-INF/view/403_error.jsp";
        }
        req.setAttribute("pages", articlesOfPeriodical.keySet());
        req.setAttribute("current_page", page);

        req.setAttribute("articles", pageOfArticles);
        req.setAttribute("periodical", periodical);

        req.getSession().setAttribute("previous_page", "/periodical?periodical_id=" + periodicalId + "&articles_page=" + page);

        return "/WEB-INF/view/articles.jsp";
    }
}


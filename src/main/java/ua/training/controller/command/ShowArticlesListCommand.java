package ua.training.controller.command;

import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.User;
import ua.training.model.service.ArticleService;
import ua.training.model.service.strategy.StrategyFactory;

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
        List<Article> purchasedArticles;
        Map<Integer, List<Article>> articlesOfPeriodical;
        List<Article> pageOfArticles;

        int periodicalId = Integer.parseInt(req.getParameter("periodical_id"));
        User user = (User) req.getSession().getAttribute("user");

        articleService.setStrategy(StrategyFactory.getStrategy(user.getRole()));
        purchasedArticles = articleService.getArticlesOfUser(user.getId());

        articlesOfPeriodical = articleService.getArticlesOfPeriodicalDividedOnPages(periodicalId, 6);

        String page = req.getParameter("articles_page");
        if (page == null) {
            page = "1";
        }

        pageOfArticles = articlesOfPeriodical.getOrDefault(Integer.parseInt(page), new ArrayList<>());

        if (!purchasedArticles.containsAll(pageOfArticles)) {
            //TODO error page
            return null;
        }
        req.setAttribute("pages", articlesOfPeriodical.keySet());
        req.setAttribute("current_page", page);

        req.setAttribute("articles", pageOfArticles);
        req.setAttribute("periodical", articleService.getPeriodicalById(periodicalId));

        return "/WEB-INF/view/articles.jsp";
    }
}


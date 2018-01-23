package ua.training.controller.command;

import ua.training.model.entity.Article;
import ua.training.model.entity.User;
import ua.training.model.entity.UserArticle;
import ua.training.model.service.ArticleService;
import ua.training.model.service.strategy.StrategyFactory;
import ua.training.util.text.constants.Attributes;
import ua.training.util.text.constants.Commands;
import ua.training.util.text.constants.Pages;
import ua.training.util.text.constants.Parameteres;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowArticleCommand implements Command {
    private ArticleService articleService;

    public ShowArticleCommand(ArticleService articleService) {
        this.articleService = articleService;
    }

    @SuppressWarnings("unchecked")
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int articleId = Integer.parseInt(req.getParameter(Parameteres.ARTICLE_ID));
        Article article;
        User user = (User) req.getSession().getAttribute(Attributes.USER);
        List<Article> availableArticles;

        articleService.setStrategy(StrategyFactory.getStrategy(user.getRole()));
        availableArticles = articleService.getArticlesOfUser(user.getId());

        article = articleService.getArticleById(articleId);

        if (!availableArticles.contains(article)) {
            return Pages.ERROR_403;
        }

        List<Article> unreadArticles = (List<Article>) req.getSession().getAttribute(Attributes.UNREAD_ARTICLES);
        if (unreadArticles.contains(article)) {
            articleService.makeArticleRead(new UserArticle.UserArticleBuilder()
                                                        .buildUser(user)
                                                        .buildArticle(article)
                                                        .buildRead(true)
                                                        .buildUserArticle());
        }

        req.setAttribute(Attributes.ARTICLE, article);

        req.getSession().setAttribute(Attributes.PREVIOUS_PAGE, Commands.ARTICLE + "?" + Parameteres.ARTICLE_ID
                                                                                                    + "=" + articleId);

        return Pages.ARTICLE;
    }
}

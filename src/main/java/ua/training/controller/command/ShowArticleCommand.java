package ua.training.controller.command;

import ua.training.model.entity.Article;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.entity.UserArticle;
import ua.training.model.service.ArticleService;
import ua.training.model.service.strategy.StrategyFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public class ShowArticleCommand implements Command {
    private ArticleService articleService;

    public ShowArticleCommand(ArticleService articleService) {
        this.articleService = articleService;
    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        int articleId = Integer.parseInt(req.getParameter("article_id"));
        Article article;
        User user = (User) req.getSession().getAttribute("user");
        List<Article> availableArticles;

        articleService.setStrategy(StrategyFactory.getStrategy(user.getRole()));
        availableArticles = articleService.getArticlesOfUser(user.getId());

        article = articleService.getArticleById(articleId);

        if (!availableArticles.contains(article)) {
            return "/WEB-INF/view/403_error.jsp";
        }

        List<Article> unreadArticles = (List<Article>) req.getSession().getAttribute("unread_articles");
        if (unreadArticles.contains(article)) {
            articleService.makeArticleRead(new UserArticle.UserArticleBuilder()
                                                        .buildUser(user)
                                                        .buildArticle(article)
                                                        .buildRead(true)
                                                        .buildUserArticle());
        }

        req.setAttribute("article", article);

        req.getSession().setAttribute("previous_page", "/periodical/article?article_id=" + articleId);

        return "/WEB-INF/view/article.jsp";
    }
}

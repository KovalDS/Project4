package ua.training.controller.command;

import ua.training.model.entity.Article;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.service.ArticleService;

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

        if (user.getRole().equals(Role.ADMIN)) { //TODO rewrite with map

            availableArticles = articleService.getAllArticles();
        } else {
            availableArticles = articleService.getArticlesOfUser(user.getId());
        }

        article = articleService.getArticleById(articleId);

        if (!availableArticles.contains(article)) {
            //TODO exception page here
            return null;
        }

        req.setAttribute("article", article);
        return "/WEB-INF/view/article.jsp";
    }
}

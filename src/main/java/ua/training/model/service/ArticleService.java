package ua.training.model.service;

import ua.training.dao.ArticleDao;
import ua.training.dao.UserArticleDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.Article;
import ua.training.model.entity.UserArticle;

import java.util.List;

public class ArticleService {
    public List<Article> getAllArticles() {
        try (ArticleDao articleDao = DaoFactory.getInstance().createArticleDao()) {
            return articleDao.findAll();
        }
    }

    public List<Article> getArticlesOfUser(int userId) {
        try (ArticleDao articleDao = DaoFactory.getInstance().createArticleDao()) {
            return articleDao.findArticlesOfUser(userId);
        }
    }

    public Article getArticleById(int articleId) {
        try (ArticleDao articleDao = DaoFactory.getInstance().createArticleDao()) {
            return articleDao.findById(articleId);
        }
    }

    public List<Article> getUnreadArticles(int userId) {
        try (ArticleDao articleDao = DaoFactory.getInstance().createArticleDao()) {
            return articleDao.findUnreadArticlesOfUser(userId);
        }
    }

    public void makeArticleRead(UserArticle userArticle) {
        try (UserArticleDao userArticleDao = DaoFactory.getInstance().createUserArticleDao()) {
            userArticleDao.update(userArticle);
        }
    }
}

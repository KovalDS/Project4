package ua.training.model.service;

import ua.training.dao.ArticleDao;
import ua.training.dao.PeriodicalDao;
import ua.training.dao.UserArticleDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.UserArticle;
import ua.training.model.service.strategy.Strategy;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ArticleService {
    private Strategy strategy;

    public List<Article> getArticlesOfUser(int userId) {
        return strategy.getAvailableArticles(userId);
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

    public Map<Integer, List<Article>> getArticlesOfPeriodicalDividedOnPages(int periodicalId, int articlesPerPage) {
        try (ArticleDao articleDao = DaoFactory.getInstance().createArticleDao()) {
            Map<Integer, List<Article>> result = new HashMap<>();
            List<Article> pageOfArticles = articleDao.findFixedNumberOfArticlesOfPeriodical(periodicalId, articlesPerPage, 0);

            for (int pageNumber = 1; !pageOfArticles.isEmpty(); pageNumber++) {
                result.put(pageNumber, pageOfArticles);
                pageOfArticles = articleDao.findFixedNumberOfArticlesOfPeriodical(periodicalId, articlesPerPage, articlesPerPage*pageNumber);
            }
            return result;
        }
    }

    public Periodical getPeriodicalById(int periodicalId) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            return periodicalDao.findById(periodicalId);
        }
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}

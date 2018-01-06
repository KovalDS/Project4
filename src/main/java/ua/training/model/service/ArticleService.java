package ua.training.model.service;

import ua.training.dao.ArticleDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.Article;

import java.util.List;

public class ArticleService {
    public List<Article> getAllArticles() {
        try (ArticleDao articleDao = DaoFactory.getInstance().createArticleDao()) {
            return articleDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Article> getArticlesOfUser(int userId) {
        try (ArticleDao articleDao = DaoFactory.getInstance().createArticleDao()) {
            return articleDao.findArticlesOfUser(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Article getArticleById(int articleId) {
        try (ArticleDao articleDao = DaoFactory.getInstance().createArticleDao()) {
            return articleDao.findById(articleId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package ua.training.model.service;

import ua.training.dao.ArticleDao;
import ua.training.dao.PeriodicalDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;

public class AdminService {
    public void createArticle(Article article) {
        try (ArticleDao articleDao = DaoFactory.getInstance().createArticleDao()) {
            articleDao.create(article);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public void createPeriodical(Periodical periodical) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            periodicalDao.create(periodical);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Periodical getPeriodicalById(int periodicalId) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            return periodicalDao.findById(periodicalId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
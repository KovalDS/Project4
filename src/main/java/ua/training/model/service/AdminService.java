package ua.training.model.service;

import ua.training.dao.PeriodicalDao;
import ua.training.dao.UserDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;
import ua.training.model.entity.User;
import ua.training.model.observer.ObservablePeriodical;
import ua.training.model.observer.UserObserver;

import java.util.List;

public class AdminService {
    public void createArticle(Article article) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {

            List<User> subscribers = userDao.findUsersSubscribedOnPeriodical(article.getPeriodical().getId());
            ObservablePeriodical observablePeriodical = new ObservablePeriodical();

            for (User user : subscribers) {
                new UserObserver(user, observablePeriodical);
            }

            observablePeriodical.addArticle(article);
        }
    }

    public void createPeriodical(Periodical periodical) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            periodicalDao.create(periodical);
        }
    }

    public Periodical getPeriodicalById(int periodicalId) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            return periodicalDao.findById(periodicalId);
        }
    }
}

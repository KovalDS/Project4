package ua.training.model.observer;

import ua.training.dao.ArticleDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.Article;
import ua.training.model.entity.User;
import ua.training.model.entity.UserArticle;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ObservablePeriodical {

    private Set<UserObserver> subscribers = new HashSet<>();

    public void addObserver(UserObserver userObserver) {
        subscribers.add(userObserver);
    }

    public void removeObserver(UserObserver userObserver) {
        subscribers.remove(userObserver);
    }

    public void addArticle(Article article) {
        try (ArticleDao articleDao = DaoFactory.getInstance().createArticleDao()) {
            articleDao.create(article);
        }
        notifySubscribers(article);
    }

    private void notifySubscribers(Article article) {
        for (UserObserver subscriber : subscribers) {
            subscriber.update(article);
        }
    }
}

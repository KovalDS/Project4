package ua.training.model.service.observer;

import ua.training.dao.UserArticleDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.Article;
import ua.training.model.entity.User;
import ua.training.model.entity.UserArticle;

public class UserObserver {
    private User user;
    private ObservablePeriodical observablePeriodical;

    public UserObserver(User user, ObservablePeriodical observablePeriodical) {
        this.user = user;
        this.observablePeriodical = observablePeriodical;
        observablePeriodical.addObserver(this);
    }

    public void update(Article article) {
        try (UserArticleDao userArticleDao = DaoFactory.getInstance().createUserArticleDao()) {
            userArticleDao.create(new UserArticle.UserArticleBuilder()
                                        .buildUser(user)
                                        .buildArticle(article)
                                        .buildRead(false)
                                        .buildUserArticle());
        }
    }
}

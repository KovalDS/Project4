package ua.training.dao.factory;

import ua.training.dao.*;

public abstract class DaoFactory {
    private static DaoFactory daoFactory;

    public abstract ArticleDao createArticleDao();
    public abstract OrderDao createOrderDao();
    public abstract PeriodicalDao createPeriodicalDao();
    public abstract UserDao createUserDao();
    public abstract UserArticleDao createUserArticleDao();

    public static DaoFactory getInstance() {
        if (daoFactory == null) {
            synchronized (DaoFactory.class) {
                if (daoFactory == null) {
                    daoFactory = new JDBCDaoFactory();
                }
            }
        }
        return daoFactory;
    }
}

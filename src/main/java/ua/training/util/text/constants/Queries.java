package ua.training.util.text.constants;

import java.util.ResourceBundle;

public class Queries {
    private static final ResourceBundle bundle = ResourceBundle.getBundle("queries/queries");

    public static final String INSERT_INTO_ARTICLE = getMessage("insert.into.article");
    public static final String SELECT_ARTICLE_BY_ID = getMessage("select.article.by.id");
    public static final String SELECT_ALL_ARTICLES = getMessage("select.all.articles");
    public static final String SELECT_ARTICLES_OF_USER = getMessage("select.articles.of.user");
    public static final String SELECT_UNREAD_ARTICLES = getMessage("select.unread.articles.of.user");
    public static final String SELECT_ARTICLES_BY_PERIODICAL = getMessage("select.articles.by.periodical");
    public static final String INSERT_INTO_ORDER = getMessage("insert.into.order");
    public static final String INSERT_INTO_ORDER_HAS_PERIODICAL = getMessage("insert.into.order.has.periodical");
    public static final String INSERT_INTO_USER_HAS_PERIODICAL = getMessage("insert.into.user.has.periodical");
    public static final String UPDATE_USER_BALANCE = getMessage("update.user.balance");
    public static final String UPDATE_ORDER_STATUS = getMessage("update.order.status");
    public static final String INSERT_INTO_PERIODICAL = getMessage("insert.into.periodical");
    public static final String SELECT_PERIODICAL_BY_ID = getMessage("select.periodical.by.id");
    public static final String SELECT_ALL_PERIODICALS = getMessage("select.all.periodicals");
    public static final String FIND_PERIODICALS_OF_USER = getMessage("find.periodicals.of.user");
    public static final String SELECT_PERIODICALS_LIMIT = getMessage("select.periodicals.limit");
    public static final String INSERT_INTO_USER_HAS_ARTICLE = getMessage("insert.into.user.has.article");
    public static final String USER_HAS_ARTICLE = getMessage("update.user.has.article");
    public static final String INSERT_INTO_USER = getMessage("insert.into.user");
    public static final String UPDATE_USER = getMessage("update.user");
    public static final String FIND_USER_BY_EMAIL = getMessage("find.user.by.email");
    public static final String FIND_USERS_BY_PERIODICAL = getMessage("find.users.by.periodical");

    private static String getMessage(String message) {
        return bundle.getString(message);
    }
}

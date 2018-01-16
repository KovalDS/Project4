package ua.training.controller.jstl;

import ua.training.controller.Util;
import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;

import java.util.List;

public class JstlFunctions {
    public static boolean containsPeriodical(List<Periodical> periodicals, Periodical periodical) {
        return periodicals.contains(periodical);
    }

    public static boolean containsArticle(List<Article> articles, Article article) {
        return articles.contains(article);
    }

    public static boolean hasUnreadArticles(List<Article> unreadArticles, List<Article> periodicalArticles) {
        if (unreadArticles == null || periodicalArticles == null) {
            return false;
        }
        return Util.listIntersection(unreadArticles, periodicalArticles).size() > 0;
    }
}

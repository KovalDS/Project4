package ua.training.dao;

import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;

import java.util.List;

public interface ArticleDao extends GenericDao<Article> {
    List<Article> findArticlesOfUser(int userId);
}

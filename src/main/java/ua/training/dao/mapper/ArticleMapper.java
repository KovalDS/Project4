package ua.training.dao.mapper;

import ua.training.model.entity.Article;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class ArticleMapper implements ObjectMapper<Article> {
    @Override
    public Article extractFromResultSet(ResultSet rs) throws SQLException {
        return new Article.ArticleBuilder()
                .buildId(rs.getInt("idarticle"))
                .buildName(rs.getString("article_name"))
                .buildText(rs.getString("text"))
                .buildDateOfPublication(rs.getDate("date_of_publication").toLocalDate())
                .buildArticle();
    }

    @Override
    public Article makeUnique(Map<Integer, Article> cache, Article object) {
        cache.putIfAbsent(object.getId(), object);
        return cache.get(object.getId());
    }
}

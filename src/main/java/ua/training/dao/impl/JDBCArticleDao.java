package ua.training.dao.impl;

import ua.training.dao.ArticleDao;
import ua.training.dao.mapper.ArticleMapper;
import ua.training.model.entity.Article;
import ua.training.model.entity.Periodical;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCArticleDao implements ArticleDao {
    private Connection connection;

    public JDBCArticleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Article entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO article (text, date_of_publication, idperiodical) VALUES (?, ?, ?)")) {
            preparedStatement.setString(1, entity.getText());
            preparedStatement.setDate(2, Date.valueOf(entity.getDateOfPublication()));
            preparedStatement.setInt(3, entity.getPeriodical().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Article findById(int id) {
        Article result = null;
        ArticleMapper articleMapper = new ArticleMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM article WHERE idarticle = (?)")) {
            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                result = articleMapper.extractFromResultSet(rs);
            }
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        ArticleMapper articleMapper = new ArticleMapper();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery("SELECT * FROM article");
            while (rs.next()) {
                articles.add(articleMapper.extractFromResultSet(rs));
            }
            return articles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Article entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public List<Article> findArticlesOfUser(int userId) {
        List<Article> articles = new ArrayList<>();
        ArticleMapper articleMapper = new ArticleMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM article LEFT JOIN periodical USING (idperiodical) LEFT JOIN order_has_periodical USING (idperiodical) LEFT JOIN project4db.order USING (idorder) LEFT JOIN project4db.user USING (iduser) WHERE iduser = (?)")) {
            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                articles.add(articleMapper.extractFromResultSet(rs));
            }
            return articles;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

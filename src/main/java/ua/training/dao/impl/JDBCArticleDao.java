package ua.training.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dao.ArticleDao;
import ua.training.dao.mapper.ArticleMapper;
import ua.training.dao.util.ConnectionUtil;
import ua.training.model.entity.Article;
import ua.training.util.text.constants.Queries;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class JDBCArticleDao implements ArticleDao {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JDBCArticleDao.class);

    public JDBCArticleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Article entity) {
        try (PreparedStatement preparedStatement = connection
                .prepareStatement(Queries.INSERT_INTO_ARTICLE, Statement.RETURN_GENERATED_KEYS)) {

            preparedStatement.setString(1, entity.getName());
            preparedStatement.setString(2, entity.getText());
            preparedStatement.setDate(3, Date.valueOf(entity.getDateOfPublication()));
            preparedStatement.setInt(4, entity.getPeriodical().getId());
            preparedStatement.executeUpdate();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            rs.next();
            entity.setId(rs.getInt(1));
        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Article findById(int id) {
        Article result = null;
        ArticleMapper articleMapper = new ArticleMapper();

        try (PreparedStatement preparedStatement = connection
                .prepareStatement(Queries.SELECT_ARTICLE_BY_ID)) {

            preparedStatement.setInt(1, id);
            ResultSet rs = preparedStatement.executeQuery();
            if (rs.next()) {
                result = articleMapper.extractFromResultSet(rs);
            }
            return result;
        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Article> findAll() {
        List<Article> articles = new ArrayList<>();
        ArticleMapper articleMapper = new ArticleMapper();

        try (Statement statement = connection.createStatement()) {
            ResultSet rs = statement.executeQuery(Queries.SELECT_ALL_ARTICLES);
            while (rs.next()) {
                articles.add(articleMapper.extractFromResultSet(rs));
            }
            return articles;
        } catch (SQLException e) {
            logger.info(e);
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

        try (PreparedStatement preparedStatement = connection
                .prepareStatement(Queries.SELECT_ARTICLES_OF_USER)) {

            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                articles.add(articleMapper.extractFromResultSet(rs));
            }
            return articles;
        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Article> findUnreadArticlesOfUser(int userId) {
        List<Article> articles = new ArrayList<>();
        ArticleMapper articleMapper = new ArticleMapper();

        try (PreparedStatement preparedStatement = connection
                .prepareStatement(Queries.SELECT_UNREAD_ARTICLES)) {

            preparedStatement.setInt(1, userId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                articles.add(articleMapper.extractFromResultSet(rs));
            }
            return articles;
        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Article> findFixedNumberOfArticlesOfPeriodical(int periodicalId, int limit, int offset) {
        List<Article> articles = new ArrayList<>();
        ArticleMapper articleMapper = new ArticleMapper();

        try (PreparedStatement preparedStatement = connection
                .prepareStatement(Queries.SELECT_ARTICLES_BY_PERIODICAL)) {

            preparedStatement.setInt(1, periodicalId);
            preparedStatement.setInt(2, limit);
            preparedStatement.setInt(3, offset);
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
        ConnectionUtil.close(connection);
    }
}

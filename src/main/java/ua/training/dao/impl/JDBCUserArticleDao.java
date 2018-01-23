package ua.training.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dao.UserArticleDao;
import ua.training.dao.util.ConnectionUtil;
import ua.training.model.entity.UserArticle;
import ua.training.util.text.constants.Queries;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCUserArticleDao implements UserArticleDao {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JDBCUserArticleDao.class);

    public JDBCUserArticleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UserArticle entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.INSERT_INTO_USER_HAS_ARTICLE)) {
            preparedStatement.setInt(1, entity.getUser().getId());
            preparedStatement.setInt(2, entity.getArticle().getId());
            preparedStatement.setBoolean(3, entity.isRead());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public UserArticle findById(int id) {
        return null;
    }

    @Override
    public List<UserArticle> findAll() {
        return null;
    }

    @Override
    public void update(UserArticle entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement(Queries.USER_HAS_ARTICLE)){
            preparedStatement.setBoolean(1, entity.isRead());
            preparedStatement.setInt(2, entity.getUser().getId());
            preparedStatement.setInt(3, entity.getArticle().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        ConnectionUtil.close(connection);
    }
}

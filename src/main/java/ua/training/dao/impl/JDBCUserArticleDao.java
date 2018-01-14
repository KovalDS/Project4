package ua.training.dao.impl;

import ua.training.dao.UserArticleDao;
import ua.training.model.entity.UserArticle;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class JDBCUserArticleDao implements UserArticleDao {
    private Connection connection;

    public JDBCUserArticleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UserArticle entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO user_has_article (iduser, idarticle, is_read) VALUES (?, ?, ?)")) {
            preparedStatement.setInt(1, entity.getUser().getId());
            preparedStatement.setInt(2, entity.getArticle().getId());
            preparedStatement.setBoolean(3, entity.isRead());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user_has_article SET is_read = (?) WHERE iduser = (?) AND idarticle = (?)")){
            preparedStatement.setBoolean(1, entity.isRead());
            preparedStatement.setInt(2, entity.getUser().getId());
            preparedStatement.setInt(3, entity.getArticle().getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}

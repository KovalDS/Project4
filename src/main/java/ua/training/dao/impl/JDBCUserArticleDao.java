package ua.training.dao.impl;

import ua.training.dao.UserArticleDao;
import ua.training.model.entity.UserArticle;

import java.sql.Connection;
import java.util.List;

public class JDBCUserArticleDao implements UserArticleDao {
    private Connection connection;

    public JDBCUserArticleDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UserArticle entity) {

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

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {

    }
}

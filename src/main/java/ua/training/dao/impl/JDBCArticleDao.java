package ua.training.dao.impl;

import ua.training.dao.ArticleDao;

import java.sql.Connection;

public class JDBCArticleDao implements ArticleDao {
    private Connection connection;

    public JDBCArticleDao(Connection connection) {
        this.connection = connection;
    }
}

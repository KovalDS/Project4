package ua.training.dao.impl;

import ua.training.dao.UserDao;

import java.sql.Connection;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }
}

package ua.training.dao.impl;

import ua.training.dao.OrderDao;

import java.sql.Connection;

public class JDBCOrderDao implements OrderDao {
    private Connection connection;

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }
}

package ua.training.dao.impl;

import ua.training.dao.PeriodicalDao;

import java.sql.Connection;

public class JDBCPeriodicalDao implements PeriodicalDao {
    private Connection connection;

    public JDBCPeriodicalDao(Connection connection) {
        this.connection = connection;
    }
}

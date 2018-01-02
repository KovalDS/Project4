package ua.training.dao.impl;

import ua.training.dao.RoleDao;

import java.sql.Connection;

public class JDBCRoleDao implements RoleDao {
    private Connection connection;

    public JDBCRoleDao(Connection connection) {
        this.connection = connection;
    }
}

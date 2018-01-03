package ua.training.dao.factory;

import ua.training.dao.*;
import ua.training.dao.impl.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();;

    @Override
    public ArticleDao createArticleDao() {
        return new JDBCArticleDao(getConnection());
    }

    @Override
    public OrderDao createOrderDao() {
        return new JDBCOrderDao(getConnection());
    }

    @Override
    public PeriodicalDao createPeriodicalDao() {
        return new JDBCPeriodicalDao(getConnection());
    }

    @Override
    public RoleDao createRoleDao() {
        return new JDBCRoleDao(getConnection());
    }

    @Override
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

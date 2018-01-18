package ua.training.dao.factory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dao.*;
import ua.training.dao.impl.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class JDBCDaoFactory extends DaoFactory {
    private DataSource dataSource = ConnectionPoolHolder.getDataSource();
    private static final Logger logger = LogManager.getLogger(JDBCDaoFactory.class);

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
    public UserDao createUserDao() {
        return new JDBCUserDao(getConnection());
    }

    @Override
    public UserArticleDao createUserArticleDao() {
        return new JDBCUserArticleDao(getConnection());
    }

    private Connection getConnection() {
        try {
            return dataSource.getConnection();
        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }
}

package ua.training.dao.impl;

import com.mysql.cj.jdbc.exceptions.MysqlDataTruncation;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dao.OrderDao;
import ua.training.dao.util.ConnectionUtil;
import ua.training.model.entity.Order;
import ua.training.model.entity.Periodical;
import ua.training.model.exception.NotEnoughBalanceException;
import ua.training.model.exception.SubscriptionDuplicationException;

import java.sql.*;
import java.util.List;
import java.util.Set;

public class JDBCOrderDao implements OrderDao {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JDBCOrderDao.class);

    public JDBCOrderDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(Order entity) {
        try (PreparedStatement insertOrderStatement = connection.prepareStatement("INSERT INTO project4db.order (status, total_price, date, iduser) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                        PreparedStatement insertOrderHasPeriodicalStatement = connection.prepareStatement("INSERT INTO order_has_periodical (idorder, idperiodical) VALUES (?, ?)");
                        PreparedStatement insertUserHasPeriodicalStatement = connection.prepareStatement("INSERT INTO user_has_periodical (iduser, idperiodical) VALUE (?, ?)");
                        PreparedStatement updateUserBalance = connection.prepareStatement("UPDATE project4db.user SET balance = balance - (?) WHERE iduser = (?)");
                        PreparedStatement updateOrderStatus = connection.prepareStatement("UPDATE project4db.order SET status = (?) WHERE idorder = (?)")) {
            int orderId;
            Set<Periodical> periodicals = entity.getPeriodicals();

            insertOrderStatement.setString(1, entity.getStatus());
            insertOrderStatement.setInt(2, entity.getTotalPrice());
            insertOrderStatement.setDate(3, Date.valueOf(entity.getDate()));
            insertOrderStatement.setInt(4, entity.getUser().getId());
            insertOrderStatement.executeUpdate();

            ResultSet rs = insertOrderStatement.getGeneratedKeys();
            rs.next();
            orderId = rs.getInt(1);

            connection.setAutoCommit(false);

            for (Periodical periodical : periodicals) {
                insertOrderHasPeriodicalStatement.setInt(1, orderId);
                insertOrderHasPeriodicalStatement.setInt(2, periodical.getId());
                insertOrderHasPeriodicalStatement.executeUpdate();

                updateUserBalance.setInt(1, periodical.getPrice());
                updateUserBalance.setInt(2, entity.getUser().getId());
                updateUserBalance.executeUpdate();

                insertUserHasPeriodicalStatement.setInt(1, entity.getUser().getId());
                insertUserHasPeriodicalStatement.setInt(2, periodical.getId());
                insertUserHasPeriodicalStatement.executeUpdate();
            }

            updateOrderStatus.setString(1, "completed");
            updateOrderStatus.setInt(2, orderId);
            updateOrderStatus.executeUpdate();
            connection.commit();

        } catch (MysqlDataTruncation e) {
            logger.info(e);
            ConnectionUtil.rollback(connection);
            throw new NotEnoughBalanceException(e.getMessage());
        } catch (SQLIntegrityConstraintViolationException e) {
            logger.info(e);
            ConnectionUtil.rollback(connection);
            throw new SubscriptionDuplicationException(e.getMessage());
        } catch (SQLException e) {
            logger.info(e);
            ConnectionUtil.rollback(connection);
        } finally {
            try {
                connection.setAutoCommit(true);
            } catch (SQLException e) {
                logger.info(e);
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public Order findById(int id) {
        return null;
    }

    @Override
    public List<Order> findAll() {
        return null;
    }

    @Override
    public void update(Order entity) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public void close() {
        ConnectionUtil.close(connection);
    }
}

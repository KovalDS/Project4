package ua.training.dao.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.training.dao.UserDao;
import ua.training.dao.mapper.UserMapper;
import ua.training.dao.util.ConnectionUtil;
import ua.training.model.entity.User;
import ua.training.model.exception.NotUniqueEmailException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private Connection connection;
    private static final Logger logger = LogManager.getLogger(JDBCUserDao.class);

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        try (PreparedStatement insertUserStatement = connection.prepareStatement("INSERT INTO project4db.user (email, password, first_name, second_name, balance, role) VALUES (?, ?, ?, ?, ?, ?)")) {
            insertUserStatement.setString(1, entity.getEmail());
            insertUserStatement.setString(2, entity.getPassword());
            insertUserStatement.setString(3, entity.getFirstName());
            insertUserStatement.setString(4, entity.getSecondName());
            insertUserStatement.setInt(5, entity.getBalance());
            insertUserStatement.setString(6, entity.getRole().toString());
            insertUserStatement.executeUpdate();

        } catch (SQLIntegrityConstraintViolationException e) {
            logger.info(e);
            throw new NotUniqueEmailException(e.getMessage());
        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public User findById(int id) {
        return null;
    }

    @Override
    public List<User> findAll() {
        return null;
    }

    @Override
    public void update(User entity) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE user SET password = (?), first_name = (?), second_name = (?), balance = (?) WHERE iduser = (?)")) {
            preparedStatement.setString(1, entity.getPassword());
            preparedStatement.setString(2, entity.getFirstName());
            preparedStatement.setString(3, entity.getSecondName());
            preparedStatement.setInt(4, entity.getBalance());
            preparedStatement.setInt(5, entity.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User findByEmail(String email) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM project4db.user WHERE email = (?)")) {
            User user = null;
            UserMapper userMapper = new UserMapper();

            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
            }
            return user;

        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<User> findUsersSubscribedOnPeriodical(int periodicalId) {
        List<User> users = new ArrayList<>();
        UserMapper userMapper = new UserMapper();

        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM project4db.user LEFT JOIN user_has_periodical USING (iduser) WHERE idperiodical = (?)")) {
            preparedStatement.setInt(1, periodicalId);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                users.add(userMapper.extractFromResultSet(rs));
            }
            return users;
        } catch (SQLException e) {
            logger.info(e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        ConnectionUtil.close(connection);
    }
}

package ua.training.dao.impl;

import ua.training.dao.UserDao;
import ua.training.dao.mapper.UserMapper;
import ua.training.dao.util.ConnectionUtil;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;
import ua.training.model.exception.NotUniqueEmailException;

import java.sql.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JDBCUserDao implements UserDao {
    private Connection connection;

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
            throw new NotUniqueEmailException("Not unique email");
        } catch (SQLException e) {
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
            throw new RuntimeException(e);
        }
    }

    @Override
    public void close() {
        ConnectionUtil.close(connection);
    }
}

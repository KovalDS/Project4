package ua.training.dao.impl;

import ua.training.dao.UserDao;
import ua.training.model.entity.User;

import java.sql.*;
import java.util.List;

public class JDBCUserDao implements UserDao {
    private Connection connection;

    public JDBCUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(User entity) {
        try (PreparedStatement insertUserStatement = connection.prepareStatement("INSERT INTO project4db.user (email, password, first_name, second_name, balance) VALUES (?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS);
                PreparedStatement insertUserRoleStatement = connection.prepareStatement("INSERT INTO user_has_role (iduser, idrole) VALUES (?, ?)")) {
            insertUserStatement.setString(1, entity.getEmail());
            insertUserStatement.setString(2, entity.getPassword());
            insertUserStatement.setString(3, entity.getFirstName());
            insertUserStatement.setString(4, entity.getSecondName());
            insertUserStatement.setInt(5, entity.getBalance());
            insertUserStatement.executeUpdate();

            ResultSet rs = insertUserStatement.getGeneratedKeys();
            rs.next();
            insertUserRoleStatement.setInt(1, rs.getInt(1));
            insertUserRoleStatement.setInt(2, 2);
            insertUserRoleStatement.executeUpdate();
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
    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

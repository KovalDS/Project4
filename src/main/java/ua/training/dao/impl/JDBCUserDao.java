package ua.training.dao.impl;

import ua.training.dao.UserDao;
import ua.training.dao.mapper.RoleMapper;
import ua.training.dao.mapper.UserMapper;
import ua.training.model.entity.Role;
import ua.training.model.entity.User;

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
    public User findByEmail(String email) {
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT * FROM project4db.user LEFT JOIN user_has_role USING (iduser) LEFT JOIN role USING (idrole) WHERE email = (?)")) {
            User user = null;
            Role role;
            Map<Integer, User> userMap = new HashMap<>();
            Map<Integer, Role> roleMap = new HashMap<>();
            UserMapper userMapper = new UserMapper();
            RoleMapper roleMapper = new RoleMapper();

            preparedStatement.setString(1, email);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                user = userMapper.extractFromResultSet(rs);
                role = roleMapper.extractFromResultSet(rs);
                user = userMapper.makeUnique(userMap, user);
                role = roleMapper.makeUnique(roleMap, role);

                user.getRoles().add(role);
            }

            return user;

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
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

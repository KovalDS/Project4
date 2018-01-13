package ua.training.dao.mapper;

import ua.training.model.entity.Role;
import ua.training.model.entity.User;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class UserMapper implements ObjectMapper<User> {
    @Override
    public User extractFromResultSet(ResultSet rs) throws SQLException {
        return new User.UserBuilder()
                    .buildId(rs.getInt("iduser"))
                    .buildEmail(rs.getString("email"))
                    .buildPassword(rs.getString("password"))
                    .buildFirstName(rs.getString("first_name"))
                    .buildSecondName(rs.getString("second_name"))
                    .buildBalance(rs.getInt("balance"))
                    .buildRole(Role.valueOf(rs.getString("role")))
                    .buildUser();
    }

    @Override
    public User makeUnique(Map<Integer, User> cache, User object) {
        cache.putIfAbsent(object.getId(), object);
        return cache.get(object.getId());
    }
}

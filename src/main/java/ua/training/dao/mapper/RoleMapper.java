package ua.training.dao.mapper;

import ua.training.model.entity.Role;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class RoleMapper implements ObjectMapper<Role> {
    @Override
    public Role extractFromResultSet(ResultSet rs) throws SQLException {
        return new Role.RoleBuilder()
                    .buildId(rs.getInt("idrole"))
                    .buildName(rs.getString("name"))
                    .buildRole();
    }

    @Override
    public Role makeUnique(Map<Integer, Role> cache, Role object) {
        cache.putIfAbsent(object.getId(), object);
        return cache.get(object.getId());
    }
}

package ua.training.dao.mapper;

import ua.training.model.entity.UserArticle;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

public class UserArticleMapper implements ObjectMapper<UserArticle> {
    @Override
    public UserArticle extractFromResultSet(ResultSet rs) throws SQLException {
        return new UserArticle.UserArticleBuilder()
                                .buildRead(rs.getBoolean("is_read"))
                                .buildUserArticle();
    }

    @Override
    public UserArticle makeUnique(Map<Integer, UserArticle> cache, UserArticle object) {  //TODO total shit here
        cache.putIfAbsent(Integer.parseInt(object.getArticle().getId() + "" + object.getUser().getId()), object);
        return cache.get(Integer.parseInt(object.getArticle().getId() + "" + object.getUser().getId()));
    }
}

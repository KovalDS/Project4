package ua.training.dao.mapper;

import ua.training.model.entity.Periodical;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Map;

public class PeriodicalMapper implements ObjectMapper<Periodical> {
    @Override
    public Periodical extractFromResultSet(ResultSet rs) throws SQLException {
        return new Periodical.PeriodicalBuilder()
                .buildId(rs.getInt("idperiodical"))
                .buildName(rs.getString("name"))
                .buildDescription(rs.getString("description"))
                .buildPrice(rs.getInt("price"))
                .buildArticles(new ArrayList<>())
                .buildPeriodical();
    }

    @Override
    public Periodical makeUnique(Map<Integer, Periodical> cache, Periodical object) {
        cache.putIfAbsent(object.getId(), object);
        return cache.get(object.getId());
    }
}

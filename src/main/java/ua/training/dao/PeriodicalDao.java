package ua.training.dao;

import ua.training.model.entity.Periodical;

import java.util.List;

public interface PeriodicalDao extends GenericDao<Periodical> {
    List<Periodical> findByUser(int userId);
}

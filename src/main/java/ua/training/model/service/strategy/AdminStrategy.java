package ua.training.model.service.strategy;

import ua.training.dao.PeriodicalDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.Periodical;

import java.util.List;

public class AdminStrategy implements Strategy {
    @Override
    public List<Periodical> getPurchasedPeriodicals(int userId) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            return periodicalDao.findAll();
        }
    }
}

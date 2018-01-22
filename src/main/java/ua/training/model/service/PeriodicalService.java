package ua.training.model.service;

import ua.training.dao.PeriodicalDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.Periodical;
import ua.training.model.service.strategy.Strategy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PeriodicalService {
    private Strategy strategy;

    public List<Periodical> getPeriodicalsOfUser(int userId) {
        return strategy.getPurchasedPeriodicals(userId);
    }

    public Periodical getPeriodicalById(int periodicalId) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            return periodicalDao.findById(periodicalId);
        }
    }

    public Map<Integer, List<Periodical>> getPeriodicalsDividedOnPages(int periodicalsPerPage) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            Map<Integer, List<Periodical>> result = new HashMap<>();
            List<Periodical> pageOfPeriodicals = periodicalDao
                    .findFixedNumberOfPeriodicals(periodicalsPerPage, 0);

            for (int pageNumber = 1; !pageOfPeriodicals.isEmpty(); pageNumber++) {
                result.put(pageNumber, pageOfPeriodicals);
                pageOfPeriodicals = periodicalDao
                        .findFixedNumberOfPeriodicals(periodicalsPerPage, periodicalsPerPage*pageNumber);
            }
            return result;
        }
    }

    public void setStrategy(Strategy strategy) {
        this.strategy = strategy;
    }
}

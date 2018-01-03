package ua.training.model.service;

import ua.training.dao.PeriodicalDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.Periodical;

import java.util.List;

public class PeriodicalService {
    public List<Periodical> getAllPeriodicals() {
        PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao();
        return periodicalDao.findAll();
    }
}

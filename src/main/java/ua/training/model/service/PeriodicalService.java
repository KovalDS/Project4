package ua.training.model.service;

import ua.training.dao.PeriodicalDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.Periodical;

import java.util.List;

public class PeriodicalService {
    public List<Periodical> getAllPeriodicals() {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            return periodicalDao.findAll();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<Periodical> getPeriodicalsOfUser(int userId) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            return periodicalDao.findByUser(userId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public Periodical getPeriodicalById(int periodicalId) {
        try (PeriodicalDao periodicalDao = DaoFactory.getInstance().createPeriodicalDao()) {
            return periodicalDao.findById(periodicalId);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

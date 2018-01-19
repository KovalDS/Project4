package ua.training.model.service.strategy;

import ua.training.model.entity.Periodical;
import ua.training.model.service.PeriodicalService;

import java.util.List;

public interface Strategy {
    List<Periodical> getPurchasedPeriodicals(int userId);
}

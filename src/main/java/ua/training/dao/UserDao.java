package ua.training.dao;

import ua.training.model.entity.Periodical;
import ua.training.model.entity.User;

import java.util.List;

public interface UserDao extends GenericDao<User> {
    User findByEmail(String email);
    List<User> findUsersSubscribedOnPeriodical(int periodicalId);
}

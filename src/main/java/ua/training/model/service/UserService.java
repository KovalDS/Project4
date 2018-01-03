package ua.training.model.service;

import ua.training.dao.UserDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.User;

public class UserService {
    public User findByEmail(String email) {
        UserDao userDao = DaoFactory.getInstance().createUserDao();
        return userDao.findByEmail(email);
    }
}

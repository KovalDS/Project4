package ua.training.model.service;

import ua.training.dao.UserDao;
import ua.training.dao.factory.DaoFactory;
import ua.training.model.entity.User;
import ua.training.model.exception.NotUniqueEmailException;

import java.sql.SQLIntegrityConstraintViolationException;

public class UserService {
    public User findByEmail(String email) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            return userDao.findByEmail(email);
        }
    }

    public void registerUser(User user) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            userDao.create(user);
        }
    }

    public void updateUser(User user) {
        try (UserDao userDao = DaoFactory.getInstance().createUserDao()) {
            userDao.update(user);
        }
    }
}

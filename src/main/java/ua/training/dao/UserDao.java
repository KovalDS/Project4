package ua.training.dao;

import ua.training.model.entity.User;

public interface UserDao extends GenericDao<User> {
    User findByEmail(String email);
}

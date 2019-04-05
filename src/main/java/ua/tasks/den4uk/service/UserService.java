package ua.tasks.den4uk.service;

import ua.tasks.den4uk.dao.UserDao;
import ua.tasks.den4uk.model.User;

import java.util.List;

public class UserService implements Service<User> {

    private UserDao userDao = new UserDao();

    @Override
    public User getById(int id) {
        return userDao.getById(id);
    }

    @Override
    public List<User> getAll() {
        return userDao.getAll();
    }

    @Override
    public User create(User user) {
        return userDao.create(user);
    }

    @Override
    public User update(User user) {
        return userDao.update(user);
    }

    @Override
    public boolean delete(int id) {
        return userDao.delete(id);
    }

    public List<User> getUsersByPosition(String position) {
        return userDao.getUsersByPosition(position);
    }

}

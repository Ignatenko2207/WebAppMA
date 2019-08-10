package com.mainacad.service;

import com.mainacad.dao.UserDAO;
import com.mainacad.model.User;

import java.util.List;

public class UserService {

    public static User create(User user) {
        return UserDAO.create(user);
    }

    public static User getAuthUser(String login, String password) {
        User user = UserDAO.findByLogin(login);
        if (user != null && user.getPassword().equals(password)) {
            return user;
        }
        return null;
    }

    public static void delete(Integer userId) {
        UserDAO.delete(userId);
    }

    public static User findByLogin(String login) {
        return UserDAO.findByLogin(login);
    }

    public static List<User> findAll() {
        return UserDAO.findAll();
    }

    public static User findById(Integer id) {
        return UserDAO.findById(id);
    }
}

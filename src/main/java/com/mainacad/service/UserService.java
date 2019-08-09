package com.mainacad.service;

import com.mainacad.dao.UserDAO;
import com.mainacad.model.User;

public class UserService {

    public static User create(User user){
        return UserDAO.create(user);
    }

    public static User update (User user){
        return UserDAO.update(user);
    }

    public static User findById(Integer id){
        return UserDAO.findById(id);
    }

    public static User findByLogin(String login){
        return UserDAO.findByLogin(login);
    }

    public static User findAll(){
        return UserDAO.findAll();
    }

    public static void delete(Integer id){
        UserDAO.delete(id);
    }

    public static User getAuthUser(String login, String password){
        User user = UserDAO.findByLogin(login);

        if (user != null && user.getPassword().equals(password)){
            return user;
        }
        return null;
    }
}

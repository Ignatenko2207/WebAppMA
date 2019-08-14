package com.mainacad.controller;

import com.mainacad.model.User;
import com.mainacad.service.UserService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if (action.equals("login")){
            String login = req.getParameter("login");
            String password = req.getParameter("password");

            User user = UserService.findUserByLoginAndPassword(login, password);
            if (user != null){
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/items.jsp");
                req.setAttribute("user", user);
                dispatcher.forward(req, resp);
            }
            else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/wrong-auth.jsp");
                dispatcher.forward(req, resp);
            }
        } else if (action.equals("register")){

            String login = req.getParameter("login");
            String password = req.getParameter("password");
            String firstName = req.getParameter("fname");
            String lastName = req.getParameter("lname");

            User user = new User(login, password, firstName, lastName);
            User savedUser;

            if (UserService.findByLogin(user.getLogin()).equals(user.getLogin())){

                // User already exists
                RequestDispatcher dispatcherWrong = req.getRequestDispatcher("/jsp/wrong-registration.jsp");
                dispatcherWrong.forward(req, resp);

            } else {

                // Creating User
                savedUser = UserService.create(user);

                RequestDispatcher dispatcherCorrect = req.getRequestDispatcher("/jsp/correct-registration.jsp");
                req.setAttribute("regis", savedUser);

                dispatcherCorrect.forward(req, resp);
            }
        }
    }
}

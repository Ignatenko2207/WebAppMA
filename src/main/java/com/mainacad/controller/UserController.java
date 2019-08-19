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

    private static HttpServletRequest req;
    private static HttpServletResponse resp;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("uid") == null) {
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/login.jsp");
            dispatcher.forward(req, resp);
        } else {
            resp.sendRedirect("../items");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        req = request;
        resp = response;

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String action = req.getParameter("action");
        String firstName = req.getParameter("first-name");
        String lastName = req.getParameter("last-name");

        if (action.isEmpty()) return;

        if (action.equals("login")) logIn(login, password);
        if (action.equals("register")) registerUser(login, password, firstName, lastName);
        if (action.equals("logout")) logOut();
    }

    private void logOut() throws IOException {
        req.getSession().invalidate();
        resp.sendRedirect("../");
    }

    private void logIn(String login, String password) throws ServletException, IOException {
        User user = UserService.findRegisteredUser(login, password);
        if (user != null) {
            req.getSession().setAttribute("user-name", user.getFirstName().charAt(0) + "." + user.getLastName());
            req.getSession().setAttribute("uid", user.getId());
            resp.sendRedirect("../items");
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/login.jsp");
            req.setAttribute("loginErr", true);
            req.setAttribute("loginErrMsg", "Wrong login or password. Try again!");
            dispatcher.forward(req, resp);
        }
    }

    private void registerUser(String login, String password, String firstName, String lastName) throws ServletException, IOException {

        User user = UserService.findByLogin(login);
        if (user != null) {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/register.jsp");
            req.setAttribute("registerErr", true);
            req.setAttribute("registerErrMsg", "Such user already exists.");
            dispatcher.forward(req, resp);
        }

        user = new User(login, password, firstName, lastName);
        user = UserService.create(user);
        if (user.getId() != null) {
            req.getSession().setAttribute("user-name", user.getFirstName().charAt(1) + "." + user.getLastName());
            req.getSession().setAttribute("uid", user.getId());
            resp.sendRedirect("../items");
        } else {
            RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/jsp/register.jsp");
            req.setAttribute("registerErr", true);
            req.setAttribute("registerErrMsg", "Something went wrong. Please try again.");
            dispatcher.forward(req, resp);
        }

    }

}

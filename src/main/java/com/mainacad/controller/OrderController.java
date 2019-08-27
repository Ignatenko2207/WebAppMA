package com.mainacad.controller;

import com.mainacad.model.Item;
import com.mainacad.model.User;
import com.mainacad.service.ItemService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

public class OrderController extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        List<Item> items = ItemService.findAll();
        req.setAttribute("items", items);

        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/items.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");

        String action = req.getParameter("action");

        if (action.equals("addToCart")) {
            User user = (User) req.getSession(false).getAttribute("user");
            if (user == null) {
                resp.sendError(403);

                return;
            }

            Integer itemId = Integer.parseInt(req.getParameter("itemId"));
            Item item = ItemService.findById(itemId);

            PrintWriter respWriter = resp.getWriter();
            resp.setCharacterEncoding("UTF-8");

            respWriter.flush();

        } else {
            super.doPost(req, resp);
        }
    }
}
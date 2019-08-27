package com.mainacad.controller;

import com.mainacad.model.Item;
import com.mainacad.service.ItemService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ItemsController extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (req.getSession().getAttribute("uid") == null) {
            resp.sendRedirect("../auth");
        } else {
            if (req.getParameter("item_id") != null) {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/item.jsp");
                Item item = ItemService.findById(Integer.parseInt(req.getParameter("item_id")));
                if (item == null) {
                    resp.sendRedirect("../items");
                } else {
                    req.setAttribute("item", item);
                    dispatcher.forward(req, resp);
                }
            } else {
                RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/items.jsp");
                req.setAttribute("items", ItemService.findAll());
                dispatcher.forward(req, resp);
            }
        }
    }
}

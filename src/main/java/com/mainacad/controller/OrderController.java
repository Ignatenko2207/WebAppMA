package com.mainacad.controller;

import com.mainacad.service.OrderService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderController extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer uid = (Integer) req.getSession().getAttribute("uid");
        String action = req.getParameter("action");
        Integer amount = Integer.valueOf(req.getParameter("amount"));


        if (action.equals("add_to_cart")) {
            Integer itemId = Integer.valueOf(req.getParameter("item_id"));
            if (amount > 0) {
                OrderService.createOrderByItemAndUserIDs(itemId, amount, uid);
            }
            resp.sendRedirect(req.getHeader("referer") + "#item" + itemId);
            return;
        }
        if (action.equals("update")) {
            Integer oid = Integer.valueOf(req.getParameter("oid"));
            if (amount < 0) {
                resp.sendRedirect(req.getHeader("referer"));
                return;
            }
            if (amount == 0) {
                OrderService.delete(oid);
                resp.sendRedirect(req.getHeader("referer"));
                return;
            }
            if (amount > 0) {
                OrderService.updateItemAmount(oid, amount);
                resp.sendRedirect(req.getHeader("referer"));
                return;
            }
        }

    }
}

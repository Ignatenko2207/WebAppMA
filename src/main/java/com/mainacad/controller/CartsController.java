package com.mainacad.controller;

import com.mainacad.model.Cart;
import com.mainacad.model.Item;
import com.mainacad.model.Order;
import com.mainacad.service.CartService;
import com.mainacad.service.OrderService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CartsController extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Integer uid = (Integer) req.getSession().getAttribute("uid");
        String cid = req.getParameter("cid");

        if (uid == null) {
            resp.sendRedirect("../auth");
            return;
        }

        Cart userCart;

        if (cid != null && cid.matches("\\d+")) {
            Cart cart = CartService.findById(Integer.valueOf(cid));
            if (!cart.getUserId().equals(uid)) {
                resp.sendError(403);
                return;
            }
            userCart = cart.getUserId().equals(uid) ? cart : CartService.findOpenCartByUser(uid);
        } else {
            userCart = CartService.findOpenCartByUser(uid);
        }


        RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/cart.jsp");
        setReqData(req, userCart);
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String action = req.getParameter("action");

        if (action.equals("confirm")) {
            Integer cid = Integer.valueOf(req.getParameter("cid"));
            RequestDispatcher dispatcher = req.getRequestDispatcher("/jsp/cart.jsp");
            Cart userCart = CartService.closeCart(cid);
            setReqData(req, userCart);
            req.setAttribute("ok", true);
            dispatcher.forward(req, resp);
        }

    }

    private void setReqData(HttpServletRequest req, Cart userCart) {
        int total = 0;
        List<List<Object>> orderItemList = new ArrayList<>();
        if (userCart != null) {
            req.setAttribute("cart", userCart);
            orderItemList = OrderService.findOrdersWithItemsByCartId(userCart.getId());
            for (List<Object> list : orderItemList) {
                Order order = (Order) list.get(0);
                Item item = (Item) list.get(1);
                total += order.getAmount() * item.getPrice();
            }
        }
        req.setAttribute("orderItemList", orderItemList);
        req.setAttribute("total", total);
    }


}

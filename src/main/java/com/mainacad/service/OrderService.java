package com.mainacad.service;

import com.mainacad.dao.OrderDAO;
import com.mainacad.model.Cart;
import com.mainacad.model.Order;

import java.util.List;

public class OrderService {

    public static Order createOrderByItemAndUserIDs(Integer itemId, Integer amount, Integer userId) {
        Order order = new Order();
        order.setItemId(itemId);
        order.setAmount(amount);
        Cart cart = CartService.getOrCreateCartForUser(userId);
        order.setCartId(cart.getId());
        return OrderDAO.create(order);
    }

    public static List<Order> getOrdersByCartId(Integer cartId) {
        return OrderDAO.findByCart(cartId);
    }

    public static void delete(Integer id) {
        OrderDAO.delete(id);
    }

    public static Order findById(Integer id) {
        return OrderDAO.findById(id);
    }

    public static List<Order> findClosedOrdersByUserIdAndPeriod(Integer userId, Long from, Long to) {
        return OrderDAO.findClosedOrdersByUserAndPeriod(userId, from, to);
    }
}

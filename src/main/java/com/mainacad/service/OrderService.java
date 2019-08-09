package com.mainacad.service;

import com.mainacad.dao.OrderDAO;
import com.mainacad.model.Cart;
import com.mainacad.model.Item;
import com.mainacad.model.Order;
import com.mainacad.model.User;

import java.util.List;

public class OrderService {

    public static Order createOrderByItemAndUser(Item item, Integer amount, User user){
        Order order = new Order();

        order.setItemId(item.getId());
        order.setAmount(amount);

        // get or create open cart
        Cart cart = CartService.findOpenCartByUser(user.getId());
        if (cart == null) {
            cart = CartService.createCartForUser(user.getId());
        }
        order.setCartId(cart.getId());
        return OrderDAO.create(order);
    }

    public static List<Order> getOrdersByCard(Cart cart){
        return OrderDAO.findByCart(cart.getId());
    }

    public static Order findById(Integer id){
        return OrderDAO.findById(id);
    }

    public List<Order> findClosedOrdersByUserAndPeriod(User user, Long from, Long to){
        return OrderDAO.findClosedOrdersByUserAndPeriod(user.getId(), from, to);
    }

    public static Order update(Order order){
        return OrderDAO.update(order);
    }

    public static void delete (Integer id){
        OrderDAO.delete(id);
    }
}
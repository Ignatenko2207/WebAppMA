package com.mainacad.service;

import com.mainacad.dao.CartDAO;
import com.mainacad.model.Cart;

import java.util.Date;
import java.util.List;

public class CartService {

    public static Cart getOrCreateCartForUser(Integer userId) {
        if (findOpenCartByUser(userId) != null) return findOpenCartByUser(userId);
        Cart cart = new Cart();
        cart.setCreationTime(new Date().getTime());
        cart.setClosed(Boolean.FALSE);
        cart.setUserId(userId);
        return CartDAO.create(cart);
    }

    public static Cart findOpenCartByUser(Integer userId) {
        return CartDAO.findOpenCartByUser(userId);
    }

    public static Cart closeOpenCartOfUser(Integer userId) {
        Cart cart = CartDAO.findOpenCartByUser(userId);
        if (cart == null) return null;
        cart.setClosed(true);
        return CartDAO.update(cart);
    }

    public static List<Cart> findAllCartsOfUser(Integer userId) {
        return CartDAO.findByUser(userId);
    }

    public static Cart findById(Integer cartId) {
        return CartDAO.findById(cartId);
    }
}

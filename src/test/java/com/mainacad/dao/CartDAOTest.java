package com.mainacad.dao;

import com.mainacad.model.Cart;
import com.mainacad.model.User;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartDAOTest {

    private static List<Cart> carts = new ArrayList<>();

    @BeforeAll
    static void setUp() {
        Cart cart = new Cart((long) 1565701239, true, 0);
        carts.add(cart);
    }

    @AfterAll
    static void tearDown() {
        carts.stream().forEach(cart -> CartDAO.delete(0));
    }

    @Test
    void create() {
        assertNull(carts.get(0).getId());
        Cart cartInDB = CartDAO.create(carts.get(0));
        assertNotNull(cartInDB);
    }


    @Test
    void delete() {
        Cart cartInDB = CartDAO.create(carts.get(0));
        Cart checkedCartInDB = CartDAO.findById(cartInDB.getId());
        CartDAO.delete(checkedCartInDB.getId());
        Cart deletedCart = CartDAO.findById(cartInDB.getId());
        assertNull(deletedCart);
    }
}
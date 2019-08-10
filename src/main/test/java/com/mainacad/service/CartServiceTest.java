package com.mainacad.service;

import com.mainacad.model.Cart;
import com.mainacad.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CartServiceTest {

    private static User userInDB;

    @BeforeAll
    private static void setUp() {

        if (!UserService.findAll().isEmpty()) {
            userInDB = UserService.findAll().get(0);
        } else {
            userInDB = UserService.create(getRandomUser());
        }

    }

    private static User getRandomUser() {
        return new User(
                "login" + (int) (Math.random() * 1000),
                "pass" + (int) (Math.random() * 1000),
                "firstName" + (int) (Math.random() * 1000),
                "lastName" + (int) (Math.random() * 1000));
    }

    @Test
    void createCartForUser() {
        Cart cartOfUserInDB = CartService.getOrCreateCartForUser(userInDB.getId());
        assertNotNull(cartOfUserInDB.getId());
        assertEquals(userInDB.getId(), cartOfUserInDB.getUserId());
    }

    @Test
    void findOpenCartByUser() {
        Cart openCartOfUser = CartService.getOrCreateCartForUser(userInDB.getId());
        assertNotNull(openCartOfUser);
        assertFalse(openCartOfUser.getClosed());
    }

    @Test
    void closeOpenCartOfUser() {
        Cart openCartOfUser = CartService.getOrCreateCartForUser(userInDB.getId());
        CartService.closeOpenCartOfUser(userInDB.getId());
        openCartOfUser = CartService.findById(openCartOfUser.getId());
        assertTrue(openCartOfUser.getClosed());
    }

    @Test
    void allCartsOfUser() {
        List<Cart> cartsOfUserInDB = CartService.findAllCartsOfUser(userInDB.getId());
        cartsOfUserInDB.stream().forEach(cart -> assertEquals(userInDB.getId(), cart.getUserId()));
    }
}
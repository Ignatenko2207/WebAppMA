package com.mainacad.service;

import com.mainacad.model.Cart;
import com.mainacad.model.Item;
import com.mainacad.model.Order;
import com.mainacad.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class OrderServiceTest {

    private static User user;
    private static Cart cart;

    @BeforeAll
    private static void setUp() {
        user = UserService.findAll().get(0);
        cart = CartService.findAllCartsOfUser(user.getId()).get(0);
    }

    @Test
    void createOrderByItemAndUserIDsAndThenDelete() {
        Item item = ItemService.findAll().get(0);
        Order order = OrderService.createOrderByItemAndUserIDs(
                item.getId(),
                (int) (Math.random() * 10),
                user.getId()
        );
        assertNotNull(order.getId());
        order = OrderService.findById(order.getId());
        assertNotNull(order);
        OrderService.delete(order.getId());
        order = OrderService.findById(order.getId());
        assertNull(order);
    }

    @Test
    void getOrdersByCartId() {
        List<Order> orders = OrderService.getOrdersByCartId(cart.getId());
        orders.forEach(order -> assertEquals(cart.getId(), order.getCartId()));
    }

    @Test
    void findClosedOrdersByUserIdAndPeriod() {
        Long from = 1565386978228L;
        Long to = 1565390710732L;
        List<Order> orders = OrderService.findClosedOrdersByUserIdAndPeriod(user.getId(), from, to);
        orders.forEach(order -> {
            Cart relatedCart = CartService.findById(order.getCartId());
            User relatedUser = UserService.findById(cart.getUserId());
            assertTrue(relatedCart.getCreationTime() >= from && relatedCart.getCreationTime() <= to);
            assertEquals(user.getId(), relatedUser.getId());
        });
    }
}
package com.mainacad.service;

import com.mainacad.model.User;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

class UserServiceTest {

    private static User userInDb;

    @BeforeAll
    private static void setUp() {
        userInDb = UserService.findAll().get(0);
    }

    private static User getRandomUser() {
        return new User(
                "login" + (int) (Math.random() * 1000),
                "pass" + (int) (Math.random() * 1000),
                "firstName" + (int) (Math.random() * 1000),
                "lastName" + (int) (Math.random() * 1000));
    }

    @Test
    void registerUser() {
        User randomUser = getRandomUser();
        assertNull(UserService.findByLogin(randomUser.getLogin()));
        randomUser = UserService.create(randomUser);
        assertNotNull(randomUser.getId());
        UserService.delete(randomUser.getId());
    }

    @Test
    void getAuthUserWithExistingUser() {
        assertNotNull(UserService.getAuthUser(userInDb.getLogin(), userInDb.getPassword()));
    }

    @Test
    void getAuthUserWithNotRegisteredUser() {
        User randomUser = getRandomUser();
        assertNull(UserService.findByLogin(randomUser.getLogin()));
        assertNull(UserService.getAuthUser(randomUser.getLogin(), randomUser.getPassword()));
    }
}
package edu.eci.cvds.tdd.library.user;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class UserTest {

    @Test
    public void testGetName() {
        User user = new User();
        user.setName("John Doe");
        assertEquals("John Doe", user.getName());
    }

    @Test
    public void testSetName() {
        User user = new User();
        user.setName("Jane Doe");
        assertEquals("Jane Doe", user.getName());
    }

    @Test
    public void testGetId() {
        User user = new User();
        user.setId("12345");
        assertEquals("12345", user.getId());
    }

    @Test
    public void testSetId() {
        User user = new User();
        user.setId("67890");
        assertEquals("67890", user.getId());
    }
}

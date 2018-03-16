package nl.piq.realworldjavaee.domain;

import org.junit.Test;

import static org.junit.Assert.*;

public class UserTest {

    private final User user = new User("tester", "test@tst.com", "secret");

    @Test
    public void creation() {
        assertNotNull(user);
    }

    @Test
    public void checkPassword_withNullPassword_isFalse() {
        assertFalse(user.checkPassword(null));
    }

    @Test
    public void checkPassword_withCorrectPassword_isTrue() {
        assertTrue(user.checkPassword("secret"));
    }
}

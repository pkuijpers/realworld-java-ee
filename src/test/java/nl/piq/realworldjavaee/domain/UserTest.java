package nl.piq.realworldjavaee.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

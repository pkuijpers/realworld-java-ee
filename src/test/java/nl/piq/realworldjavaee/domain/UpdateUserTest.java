package nl.piq.realworldjavaee.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class UpdateUserTest {

    private User user;

    @BeforeEach
    public void setUp() {
        user = new User("testUser", "test@test.nl", "12345");
    }

    @Test
    public void username_canBeUpdated() {
        String newUsername = "newUsername";
        user.setUsername(newUsername);

        assertThat(user.getUsername()).isEqualTo(newUsername);
    }

    @Test
    public void password_canBeUpdated() {
        String newPassword = "newPassword";
        user.setPassword(newPassword);

        assertThat(user.hasPassword(newPassword)).isTrue();
    }

    @Test
    public void email_canBeUpdated() {
        String newEmail = "newEmail@test.nl";
        user.setEmail(newEmail);

        assertThat(user.getEmail()).isEqualTo(newEmail);
    }

    @Test
    public void bio_canBeUpdated() {
        String newBio = "User bio";
        user.setBio(newBio);

        assertThat(user.getBio()).isEqualTo(newBio);
    }

    @Test
    public void image_canBeUpdated() throws Exception {
        URL imageUrl = new URL("http://test.nl/image.png");
        user.setImage(imageUrl);

        assertThat(user.getImage()).isEqualTo(imageUrl);
    }
}

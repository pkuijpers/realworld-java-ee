package nl.piq.realworldjavaee.domain;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileTest {

    private UserRepository userRepository = new InMemoryUserRepository();
    private AuthenticationProvider auth = new AuthenticationProvider(userRepository);
    private String username = "username";
    private User user;

    @BeforeEach
    public void setUp() throws Exception {
        user = auth.registerUser(username, "username@test.nl", "password");
        user.setBio("This is my bio");
        user.setImage(new URL("http://google.nl/image"));
    }

    @Test
    public void getProfile_withoutUsername_throwsException() {
       Assertions.assertThrows(NotFoundException.class, () ->
            userRepository.getProfile(null));
    }

    @Test
    public void getProfile_withExistingUsername_returnsProfile() {
        Profile profile = userRepository.getProfile(username);

        assertThat(profile.getUsername()).isEqualTo(user.getUsername());
        assertThat(profile.getBio()).isEqualTo(user.getBio());
        assertThat(profile.getImage()).isEqualTo(user.getImage());
    }

    @Test
    public void getProfile_whenUserNotFound_throwsException() {
        Assertions.assertThrows(NotFoundException.class, () ->
            userRepository.getProfile("doesNotExist"));
    }

}

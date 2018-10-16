package nl.piq.realworldjavaee.domain;

import org.junit.Test;

import java.net.URL;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileTest {

    private UserRepository userRepository = new InMemoryUserRepository();

    @Test(expected = NullPointerException.class)
    public void getProfile_withoutUsername_throwsException() {
        userRepository.getProfile(null);
    }

    @Test
    public void getProfile_withExistingUsername_returnsProfile() throws Exception {
        AuthenticationProvider auth = new AuthenticationProvider(userRepository);
        String username = "username";
        User user = auth.registerUser(username, "username@test.nl", "password");
        user.setBio("This is my bio");
        user.setImage(new URL("http://google.nl/image"));

        Profile profile = userRepository.getProfile(username);

        assertThat(profile.getUsername()).isEqualTo(user.getUsername());
        assertThat(profile.getBio()).isEqualTo(user.getBio());
        assertThat(profile.getImage()).isEqualTo(user.getImage());
    }

    @Test(expected = NotFoundException.class)
    public void getProfile_whenUserNotFound_throwsException() {
        userRepository.getProfile("doesNotExist");
    }
}

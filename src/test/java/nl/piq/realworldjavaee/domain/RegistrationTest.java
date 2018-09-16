package nl.piq.realworldjavaee.domain;

import org.junit.Test;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

public class RegistrationTest {

    private UserRepository userRepo = new InMemoryUserRepository();
    private AuthenticationProvider authProvider = new AuthenticationProvider(userRepo);

    @Test(expected = NullPointerException.class)
    public void register_withMissingUsername_throwsException() {
        authProvider.registerUser(null, "test@test.nl", "test");
    }

    @Test(expected = NullPointerException.class)
    public void register_withMissingEmail_throwsException() {
        authProvider.registerUser("test", null, "test");
    }

    @Test(expected = NullPointerException.class)
    public void register_withMissingPassword_throwsException() {
        authProvider.registerUser("test", "test@test.nl", null);
    }

    @Test
    public void register_withParameters_returnsNewUser() {
        User user = authProvider.registerUser("Jacob", "jake@jake.jake", "jakejake");

        assertThat(user.getUsername()).isEqualTo("Jacob");
        assertThat(user.getEmail()).isEqualTo("jake@jake.jake");
        assertThat(user.hasPassword("jakejake")).isTrue();
    }
}

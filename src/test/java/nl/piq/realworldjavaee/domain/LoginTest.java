package nl.piq.realworldjavaee.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {

    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";

    private final UserRepository userRepo = new InMemoryUserRepository();
    private final AuthenticationProvider auth = new AuthenticationProvider(userRepo);

    private User user;

    @Before
    public void setUp() {
        user = auth.registerUser(USER, "user@test.nl", PASSWORD);
    }

    @Test(expected = UnauthorizedException.class)
    public void login_withIncorrectCredentials_throwsException() {
        auth.login("dummy", "dummy");
    }

    @Test
    public void login_withCorrectCredentials_returnsUser() {
        User loggedIn = auth.login(USER, PASSWORD);

        assertThat(loggedIn).isEqualTo(user);
    }

    @Test(expected = UnauthorizedException.class)
    public void login_withIncorrectPassword_throwsException() {
        auth.login(USER, "wrongPassword");
    }

    @Test(expected = UnauthorizedException.class)
    public void currentUser_whenNotLoggedIn_throwsException() {
        auth.currentUser();
    }

    @Test
    public void currentUser_whenLoggedIn_returnsUser() {
        user = auth.login(USER, PASSWORD);

        User currentUser = auth.currentUser();

        assertThat(currentUser).isEqualTo(user);
    }

    @Test(expected = UnauthorizedException.class)
    public void currentUser_afterLoginFailure_throwsException() {
        try {
            auth.login(USER, "wrongPassword");
        } catch (UnauthorizedException e) {
            // continue
        }

        auth.currentUser();
    }
}

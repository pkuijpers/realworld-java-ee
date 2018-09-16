package nl.piq.realworldjavaee.domain;

import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {

    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";

    private final UserRepository userRepo = new InMemoryUserRepository();
    private final AuthenticationProvider auth = new AuthenticationProvider(userRepo);

    @Before
    public void setUp() {
        auth.registerUser(USER, "user@test.nl", PASSWORD);
    }

    @Test(expected = UnauthorizedException.class)
    public void login_withIncorrectCredentials_throwsException() {
        auth.login("dummy", "dummy");
    }

    @Test
    public void login_withCorrectCredentials_returnsUser() {
        User user = auth.login(USER, PASSWORD);

        assertThat(user.getUsername()).isEqualTo(USER);
    }

    @Test(expected = UnauthorizedException.class)
    public void login_withIncorrectPassword_throwsException() {
        auth.login(USER, "wrongPassword");
    }

}

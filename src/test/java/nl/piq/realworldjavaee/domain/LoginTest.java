package nl.piq.realworldjavaee.domain;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class LoginTest {

    private static final String USER = "scott";
    private static final String PASSWORD = "tiger";

    private final UserRepository userRepo = new TestingUserRepository();
    private final AuthenticationProvider auth = new AuthenticationProvider(userRepo);

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

    private static class TestingUserRepository implements UserRepository {
        @Override
        public User find(String name) {
            if (USER.equals(name)) {
                return new User(USER, null, PASSWORD);
            }
            return null;
        }
    }
}

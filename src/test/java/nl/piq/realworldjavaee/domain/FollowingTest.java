package nl.piq.realworldjavaee.domain;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FollowingTest {

    private UserRepository userRepository = new InMemoryUserRepository();
    private AuthenticationProvider auth = new AuthenticationProvider(userRepository);
    private String username = "username";
    private User userA, userB;

    @Before
    public void setUp() {
        userA = auth.registerUser("userA", "userA@test.nl", "userApw" );
        userB = auth.registerUser("userB", "userB@test.nl", "userBpw" );
        // UserA is following userB
        userA.follow(userRepository.getProfile("userB"));
    }

    @Test(expected = NullPointerException.class)
    public void follow_withNullParameter_throwsException() {
        userA.follow(null);
    }

    @Test
    public void follow_withProfile_startsFollowing() {
        Profile profileA = userRepository.getProfile("userA");
        assertThat(userB.isFollowing(profileA)).isFalse();

        userB.follow(profileA);

        assertThat(userB.isFollowing(profileA)).isTrue();
    }

    @Test
    public void isFollowing_whenNotFollowing_returnsFalse() {
        Profile profileA = userRepository.getProfile(userA.getUsername());

        assertThat(userB.isFollowing(profileA)).isFalse();
    }

    @Test
    public void getProfile_whenFollowing_returnsProfileWithFollowingTrue() {
        Profile profile = userRepository.getProfileAsUser("userB", userA);
        assertThat(userA.isFollowing(profile)).isTrue();

        assertThat(profile.isFollowing()).isTrue();
    }

    @Test
    public void getProfile_whenNotFollowing_returnsProfileWithFollowingFalse() {
        Profile profileA = userRepository.getProfileAsUser("userA", userB);

        assertThat(profileA.isFollowing()).isFalse();
    }

    @Test
    public void getProfile_withoutCurrentUser_returnsProfileWithFollowingFalse() {
        Profile profileA = userRepository.getProfile("userA");

        assertThat(profileA.isFollowing()).isFalse();
    }

    @Test(expected = NullPointerException.class)
    public void unfollow_withNullParameter_throwsException() {
        userA.unfollow(null);
    }

    @Test
    public void unfollow_whenFollowing_stopsFollowing() {
        Profile profileB = userRepository.getProfileAsUser("userB", userA);
        assertThat(userA.isFollowing(profileB)).isTrue();

        userA.unfollow(profileB);

        assertThat(userA.isFollowing(profileB)).isFalse();
    }

    @Test
    public void unfollow_whenNotFollowing_doesNothing() {
        Profile profileA = userRepository.getProfileAsUser("userA", userB);
        assertThat(userB.isFollowing(profileA)).isFalse();

        userB.unfollow(profileA);

        assertThat(userB.isFollowing(profileA)).isFalse();
    }
}

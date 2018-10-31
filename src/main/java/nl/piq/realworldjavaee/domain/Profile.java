package nl.piq.realworldjavaee.domain;

import java.net.URL;
import java.util.Objects;

/**
 * A user profile. Use {@link UserRepository#getProfile(String)} or
 * {@link UserRepository#getProfileAsUser(String, User)} to get a Profile for an existing user.
 */
public class Profile {

    private final User user;
    private final boolean following;

    Profile(User user, User currentUser) {
        this.user = user;
        this.following = currentUser.isFollowing(this);
    }

    public String getUsername() {
        return user.getUsername();
    }

    public String getBio() {
        return user.getBio();
    }

    public URL getImage() {
        return user.getImage();
    }

    public boolean isFollowing() {
        return following;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Profile profile = (Profile) o;
        return Objects.equals(user, profile.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(user);
    }
}

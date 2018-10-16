package nl.piq.realworldjavaee.domain;

import java.net.URL;

public class Profile {

    private final User user;

    public Profile(User user) {
        this.user = user;
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
}

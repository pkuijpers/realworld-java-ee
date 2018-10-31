package nl.piq.realworldjavaee.domain;

public interface UserRepository {
    void save(User user);

    User find(String name);

    /**
     * @return the profile.
     */
    Profile getProfile(String username) throws NotFoundException;

    /**
     * @return the profile with 'following' indicator if current user is following the profile.
     */
    Profile getProfileAsUser(String username, User currentUser);
}

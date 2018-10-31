package nl.piq.realworldjavaee.domain;

import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

class InMemoryUserRepository implements UserRepository {

    private ConcurrentMap<String, User> users = new ConcurrentHashMap<>();

    @Override
    public void save(User user) {
        users.put(user.getUsername(), user);
    }

    @Override
    public User find(String name) {
        return users.get(name);
    }

    @Override
    public Profile getProfile(String username) throws NotFoundException {
        return getProfileAsUser(username, User.ANONYMOUS);
    }

    @Override
    public Profile getProfileAsUser(String username, User currentUser) {
        Objects.requireNonNull(username);
        Objects.requireNonNull(currentUser);

        User user = find(username);
        if (user == null) {
            throw new NotFoundException("User " + username + " not found.");
        }
        return new Profile(user, currentUser);
    }
}

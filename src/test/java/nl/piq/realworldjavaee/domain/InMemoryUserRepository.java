package nl.piq.realworldjavaee.domain;

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
}

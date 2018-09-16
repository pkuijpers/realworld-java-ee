package nl.piq.realworldjavaee.domain;

public interface UserRepository {
    void save(User user);
    User find(String name);
}

package nl.piq.realworldjavaee.domain;

public interface UserRepository {
    User find(String name);
}

package nl.piq.realworldjavaee.domain;

class AuthenticationProvider {

    private final UserRepository userRepo;

    AuthenticationProvider(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    User login(String username, String password) {
        User user = userRepo.find(username);
        if (user != null && user.hasPassword(password)) {
            return user;
        }
        throw new UnauthorizedException();
    }

    User registerUser(String username, String email, String password) {
        User user = new User(username, email, password);
        userRepo.save(user);
        return user;
    }
}

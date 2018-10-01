package nl.piq.realworldjavaee.domain;

class AuthenticationProvider {

    private final UserRepository userRepo;

    AuthenticationProvider(UserRepository userRepo) {
        this.userRepo = userRepo;
    }

    private User loggedInUser;

    User login(String username, String password) {
        User user = userRepo.find(username);
        if (user != null && user.hasPassword(password)) {
            this.loggedInUser = user;
            return user;
        }
        throw new UnauthorizedException();
    }

    User registerUser(String username, String email, String password) {
        User user = new User(username, email, password);
        userRepo.save(user);
        return user;
    }

    /**
     * @return the currently logged in user
     * @throws UnauthorizedException when not logged in
     */
    public User currentUser() {
        if (loggedInUser == null) {
            throw new UnauthorizedException("Not logged in");
        }
        return loggedInUser;
    }
}

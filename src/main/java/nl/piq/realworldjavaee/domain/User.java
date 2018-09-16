package nl.piq.realworldjavaee.domain;

import java.util.Objects;

public class User {
	private String username;
	private String email;

	private Password password;

	User(String username, String email, String password) {
		Objects.requireNonNull(username);
		Objects.requireNonNull(email);
		Objects.requireNonNull(password);

		this.username = username;
		this.email = email;
		this.password = new Password(password);
	}

	String getUsername() {
		return username;
	}

	String getEmail() {
		return email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(username, user.username) &&
				Objects.equals(email, user.email);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, email);
	}

	boolean hasPassword(String password) {
	    return this.password.matches(password);
	}

}
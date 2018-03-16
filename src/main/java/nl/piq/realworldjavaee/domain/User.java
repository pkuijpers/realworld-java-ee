package nl.piq.realworldjavaee.domain;

import java.util.Objects;

public class User {
	private String username;
	private String email;
	private String passwordHash;

	public User(String username, String email, String password) {
		this.username = username;
		this.email = email;
		this.passwordHash = password;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		User user = (User) o;
		return Objects.equals(username, user.username) &&
				Objects.equals(email, user.email) &&
				Objects.equals(passwordHash, user.passwordHash);
	}

	@Override
	public int hashCode() {
		return Objects.hash(username, email, passwordHash);
	}

	public boolean checkPassword(String password) {
		return password != null && password.equals(passwordHash);
	}
}
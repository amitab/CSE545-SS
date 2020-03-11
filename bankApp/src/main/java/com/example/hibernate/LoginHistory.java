package com.example.hibernate;
// Generated Mar 11, 2020, 5:05:15 PM by Hibernate Tools 5.4.7.Final

/**
 * LoginHistory generated by hbm2java
 */
public class LoginHistory implements java.io.Serializable {

	private LoginHistoryId id;
	private User user;

	public LoginHistory() {
	}

	public LoginHistory(LoginHistoryId id, User user) {
		this.id = id;
		this.user = user;
	}

	public LoginHistoryId getId() {
		return this.id;
	}

	public void setId(LoginHistoryId id) {
		this.id = id;
	}

	public User getUser() {
		return this.user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}

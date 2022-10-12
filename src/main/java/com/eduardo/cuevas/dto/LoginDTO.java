package com.eduardo.cuevas.dto;

public class LoginDTO {

	private String firstName;
	private String lastName;
	private String usernameOrEmail;
	private String password;
	private byte avatar;

	public String getUsernameOrEmail() {
		return usernameOrEmail;
	}

	public void setUsernameOrEmail(String usernameOrEmail) {
		this.usernameOrEmail = usernameOrEmail;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public byte getAvatar() {
		return avatar;
	}

	public void setAvatar(byte avatar) {
		this.avatar = avatar;
	}

}

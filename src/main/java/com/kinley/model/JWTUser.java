package com.kinley.model;

public class JWTUser {
	
	private String userName;
	private Long id;
	private String role;
	
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	@Override
	public String toString() {
		return "JWTUser [userName=" + userName + ", id=" + id + ", role=" + role + "]";
	}	
}

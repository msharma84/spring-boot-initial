package com.kinley.model;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public class JWTAuthenticationToken extends UsernamePasswordAuthenticationToken {

	private static final long serialVersionUID = 1L;
	
	private String token;
	
	public JWTAuthenticationToken(String token) {
		super(null, null);
		this.setToken(token);
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	@Override
	public Object getCredentials() {
		return null;
	}

	@Override
	public Object getPrincipal() {
		return null;
	}
	
	

}

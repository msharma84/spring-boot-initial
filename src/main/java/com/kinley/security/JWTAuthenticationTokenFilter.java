package com.kinley.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;

import com.kinley.model.JWTAuthenticationToken;

public class JWTAuthenticationTokenFilter extends AbstractAuthenticationProcessingFilter{

	public JWTAuthenticationTokenFilter() {
		super("/rest/**");
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		
		String header = request.getHeader("x-auth-token");
		
		if(header == null || !header.startsWith("token:")){
			throw new RuntimeException("JWT Token is missing");
		}
		
		String authenticationToken = header.substring(6);
		
		JWTAuthenticationToken token = new JWTAuthenticationToken(authenticationToken);
		
		return getAuthenticationManager().authenticate(token);
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		super.successfulAuthentication(request, response, chain, authResult);
		chain.doFilter(request, response);
	}

}
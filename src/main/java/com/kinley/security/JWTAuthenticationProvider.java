package com.kinley.security;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.kinley.model.JWTAuthenticationToken;
import com.kinley.model.JWTUser;
import com.kinley.model.JWTUserDetails;

@Component
public class JWTAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider  {

	@Autowired
	private JWTValidator validator;
	
	@Override
	public boolean supports(Class<?> aClass) {
		return (JWTAuthenticationToken.class.isAssignableFrom(aClass));
	}
	
	@Override
	protected UserDetails retrieveUser(String userName, UsernamePasswordAuthenticationToken userNamePasswordToken)
			throws AuthenticationException {
		
		JWTAuthenticationToken jwtToken = (JWTAuthenticationToken)userNamePasswordToken;
		String token = jwtToken.getToken();
		
		JWTUser jwtUser = validator.validate(token);
		
		if(jwtUser == null){
			throw new RuntimeException("JWT Token is incorrect");
		}
		List<GrantedAuthority> grantedAuthorities = AuthorityUtils.commaSeparatedStringToAuthorityList(jwtUser.getRole());
        return new JWTUserDetails(jwtUser.getUserName(), jwtUser.getId(),token,grantedAuthorities);
	}
	
	@Override
	protected void additionalAuthenticationChecks(UserDetails userName, UsernamePasswordAuthenticationToken userNamePasswordToken)
			throws AuthenticationException {
		
	}



}

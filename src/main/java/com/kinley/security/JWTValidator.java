/**
 * 
 * 
 * */

package com.kinley.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kinley.model.JWTUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;

@Component
public class JWTValidator {
	
	private static final Logger logger = LoggerFactory.getLogger(JWTValidator.class);
	
	private final String secretKey = "kinley";
	
	public JWTUser validate(String token){
		
		if(logger.isInfoEnabled()){
			logger.info("validating the token...");
		}
		
		JWTUser jwtUser = null;
		
		try{
			Claims claims = Jwts.parser()
							.setSigningKey(secretKey)
							.parseClaimsJws(token)
							.getBody();
			
			if(logger.isInfoEnabled()){
				logger.info("parse claims from the token...{}",claims);
			}
			
			jwtUser = new JWTUser();
			
			jwtUser.setUserName(claims.getSubject());	
			jwtUser.setId(Long.parseLong(claims.get("userId").toString()));
			jwtUser.setRole((String) claims.get("role"));
		
		}catch(Exception e){
			logger.error("Exception occurred while validating the jwt...{}",jwtUser);
		}
		return jwtUser;
	}
}
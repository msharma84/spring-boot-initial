package com.kinley.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kinley.model.JWTUser;
import com.kinley.rest.SampleRestController;
import com.kinley.security.JWTTokenGenerator;

@RestController
@RequestMapping("/token")
public class TokenController {
	
	@Autowired
	JWTTokenGenerator tokenGenerator;
	
	private static final Logger logger = LoggerFactory.getLogger(TokenController.class);
	
	@PostMapping
	public String generateToken(@RequestBody final JWTUser jwtUser){
		
		logger.info("generating token for jwt...");
		return tokenGenerator.generate(jwtUser);
	}

}

package com.kinley.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.kinley.model.JWTUser;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTTokenGenerator {

	private final String secretKey = "kinley";
	private static final Logger logger = LoggerFactory.getLogger(JWTTokenGenerator.class);
	
	public String generate(JWTUser jwtUser){
		
		/*String jweString = null;
		SecretKey secretKey = null;
		// Generate 256-bit AES key for HMAC as well as encryption
		KeyGenerator keyGen;
		try {
			keyGen = KeyGenerator.getInstance("AES");
			keyGen.init(256);
			secretKey =  keyGen.generateKey();
			
			// Create HMAC signer
			JWSSigner signer = new MACSigner(secretKey.getEncoded());
			// Prepare JWT with claims set
			JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
			    .subject("alice")
			    .issueTime(new Date())
			    .issuer("https://c2id.com")
			    .build();
			
			SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

			// Apply the HMAC
			signedJWT.sign(signer);

			// Create JWE object with signed JWT as payload
			JWEObject jweObject = new JWEObject(
			    new JWEHeader.Builder(JWEAlgorithm.DIR, EncryptionMethod.A256GCM)
			        .contentType("JWT") // required to signal nested JWT
			        .build(),
			    new Payload(signedJWT));

			// Perform encryption
			jweObject.encrypt(new DirectEncrypter(secretKey.getEncoded()));
			
			jweString = jweObject.serialize();
			
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (KeyLengthException e) {
			e.printStackTrace();
		} catch (JOSEException e) {
			e.printStackTrace();
		}
		
		return jweString;*/

		Claims claims = Jwts.claims().setSubject(jwtUser.getUserName());
		claims.put("userId", jwtUser.getId());
		claims.put("role", jwtUser.getRole());
		
		String token = Jwts.builder().setClaims(claims).signWith(SignatureAlgorithm.HS512, secretKey).compact(); 
		if(logger.isInfoEnabled()){
			logger.info("generated token...{}",token);
		}
		return token;
	}
}

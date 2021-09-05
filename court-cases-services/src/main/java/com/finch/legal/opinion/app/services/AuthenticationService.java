package com.finch.legal.opinion.app.services;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.apache.commons.codec.binary.Base64;
import org.springframework.stereotype.Service;

import com.finch.legal.opinion.app.exceptions.UnAuthorizedAccessException;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jdk.internal.org.jline.utils.Log;

/**
 * Authentication service
 * @author 91948
 *
 */
@Service
public class AuthenticationService {
	
	
	/** secret key **/
	private static final String secret = "asdfSFS34wfsdfsdfSDSD32dfsddDDerQSNCK34SOWEK5354fdgdf4";
	/**
	 * default constructor
	 */
	public AuthenticationService() {
		
	}
	
	
	/**
	 * is Authentication Valid
	 */
	public boolean isAuthenticationValid(String authHeaderValue) throws UnAuthorizedAccessException{
		
		 Key hmacKey=null;
		 Jws<Claims> jwt=null;
		 long timeExpiry=0;
		 
		System.out.println(" BBBBBBBBBBBBBBBBBBBBBB    "+authHeaderValue);
		if(authHeaderValue==null || authHeaderValue.trim().length()<1) {
			throw new UnAuthorizedAccessException("Un-Authorized Access No JWT Token Present ");
		}else if(!authHeaderValue.trim().startsWith("Bearer")) {
			throw new UnAuthorizedAccessException("Un-Authorized Access No JWT Token Present ");
		}
		
		
		 
		try {
			
			if(authHeaderValue!=null && authHeaderValue.startsWith("Bearer")) {
				authHeaderValue = authHeaderValue.replace("Bearer", "");
			}
			hmacKey = new SecretKeySpec(Base64.decodeBase64(secret), 
			                                    SignatureAlgorithm.HS256.getJcaName());
	
			jwt = Jwts.parserBuilder()
			            .setSigningKey(hmacKey)
			            .build()
			            .parseClaimsJws(authHeaderValue);
	
			 timeExpiry = jwt.getBody().getExpiration().getTime();
			  
			  if(((System.currentTimeMillis()-timeExpiry)/1000)>(60*60)) {
				  throw new UnAuthorizedAccessException("Un-Authorized Access No JWT Token Present ");
			  }
		}catch(Exception e) {
			e.printStackTrace();
		}
		return true;
	}
	
	
	/**
	 * is token valid
	 */
	private boolean isTokenValid(String bearerToken) {
		
		return true;
	}
	
	
	/**
	 * is Authentication Valid
	 */
	public String getUserName(String authHeaderValue) throws UnAuthorizedAccessException{
		
		
		return authHeaderValue;
	}
	/**
	 * is Authentication Valid
	 */
	public String getUserId(String authHeaderValue) throws UnAuthorizedAccessException{
		
		
		return authHeaderValue;
	}

}

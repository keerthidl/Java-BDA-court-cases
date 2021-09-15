package com.finch.legal.opinion.app.services;

import java.security.Key;

import javax.crypto.spec.SecretKeySpec;

import org.springframework.stereotype.Service;

import com.finch.common.logger.AppLogger;
import com.finch.common.logger.LogManager;
import com.finch.legal.opinion.app.exceptions.UnAuthorizedAccessException;
import com.finch.legal.opinion.app.util.JSONFormatter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 * Authentication service
 * @author 91948
 *
 */
@Service
public class AuthenticationService {
	
	
	/** secret key **/
	private static final String secret = "javainusebdafinchcases4hfghhtrtyrtytrteteteteetetee";
	
	/** LOGGER **/
	private static AppLogger LOG = LogManager.getLogger(AuthenticationService.class);
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
		 
	
		if(authHeaderValue==null || authHeaderValue.trim().length()<1) {
			throw new UnAuthorizedAccessException("Un-Authorized Access No JWT Token Present ");
		}else if(!authHeaderValue.trim().startsWith("Bearer")) {
			throw new UnAuthorizedAccessException("Un-Authorized Access No JWT Token Present ");
		}
		
		
		 
		try {
			
			if(authHeaderValue!=null && authHeaderValue.startsWith("Bearer")) {
				authHeaderValue = authHeaderValue.replace("Bearer", "");
			}
		
			hmacKey = new SecretKeySpec(secret.getBytes(), 
			                                    SignatureAlgorithm.HS256.getJcaName());
	
			
			jwt = Jwts.parserBuilder()
			            .setSigningKey(hmacKey)
			            .build()
			            .parseClaimsJws(authHeaderValue);
			
	
			 timeExpiry = jwt.getBody().getExpiration().getTime();
			 
			  
			  if(((System.currentTimeMillis()-timeExpiry)/1000)>(86400)) {
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
		 Key hmacKey=null;
		 Jws<Claims> jwt=null;
		 long timeExpiry=0;
		 String userId="ADMIN";
		 
		 JWTTokenDetails jwtTokenDetails=null;
		try {
		
			if(authHeaderValue!=null && authHeaderValue.startsWith("Bearer")) {
				authHeaderValue = authHeaderValue.replace("Bearer", "");
			}
		
			hmacKey = new SecretKeySpec(secret.getBytes(), 
			                                    SignatureAlgorithm.HS256.getJcaName());
	
			
			jwt = Jwts.parserBuilder()
			            .setSigningKey(hmacKey)
			            .build()
			            .parseClaimsJws(authHeaderValue);
			
		
			 timeExpiry = jwt.getBody().getExpiration().getTime();
			 
			
			 if(jwt.getBody().containsKey("firstName")) {
				 userId = ""+jwt.getBody().get("firstName");
			 }
			 
			 if(jwt.getBody().containsKey("lastName")) {
				 userId = ""+userId+"  "+jwt.getBody().get("lastName");
			 }
		
			 
		}catch(Exception e) {
			e.printStackTrace();
		}
		
		return userId;
	}

}

class JWTTokenDetails{
	
	/** id **/
	private String id;
	
	/** id **/
	private String firstName;
	
	/** id **/
	private String lastName;
	
	/** id **/
	private String email;
	
	/** id **/
	private String phone_no;
	
	/** id **/
	private String role;
	
	/** id **/
	private String iat;
	
	/** id **/
	private String exp;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the phone_no
	 */
	public String getPhone_no() {
		return phone_no;
	}

	/**
	 * @param phone_no the phone_no to set
	 */
	public void setPhone_no(String phone_no) {
		this.phone_no = phone_no;
	}

	/**
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * @param role the role to set
	 */
	public void setRole(String role) {
		this.role = role;
	}

	/**
	 * @return the iat
	 */
	public String getIat() {
		return iat;
	}

	/**
	 * @param iat the iat to set
	 */
	public void setIat(String iat) {
		this.iat = iat;
	}

	/**
	 * @return the exp
	 */
	public String getExp() {
		return exp;
	}

	/**
	 * @param exp the exp to set
	 */
	public void setExp(String exp) {
		this.exp = exp;
	}
	
	
	

}

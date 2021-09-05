package com.finch.legal.opinion.app.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author finch
 *
 */
@ResponseStatus(HttpStatus.UNAUTHORIZED)
public class UnAuthorizedAccessException extends RuntimeException{
	
	/** serial number **/
    private static final long serialVersionUID = 1L;

    
    /**
     * default constructor
     * @param message
     */
    public UnAuthorizedAccessException(String message) {
        super(message);
    }
}

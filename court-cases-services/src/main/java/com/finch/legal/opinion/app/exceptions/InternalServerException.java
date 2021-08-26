package com.finch.legal.opinion.app.exceptions;



import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * 
 * @author finch
 *
 */
@ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
public class InternalServerException extends RuntimeException{
	
	/** serial number **/
    private static final long serialVersionUID = 1L;

    
    /**
     * default constructor
     * @param message
     */
    public InternalServerException(String message) {
        super(message);
    }
}

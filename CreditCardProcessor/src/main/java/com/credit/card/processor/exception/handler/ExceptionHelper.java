package com.credit.card.processor.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.credit.card.processor.exception.InvalidInputException;
import com.credit.card.processor.pojo.CardResponse;


/**
 * Helper class for Exception Handling
 *
 */
@ControllerAdvice
public class ExceptionHelper {
	
	@ExceptionHandler(value = {InvalidInputException.class,NumberFormatException.class})
	public ResponseEntity<Object> handleException(InvalidInputException e){
		CardResponse cardResponse = new CardResponse();
		cardResponse.setErrorMessage(e.getMessage());
		return new ResponseEntity<Object>(cardResponse,HttpStatus.BAD_REQUEST);
	}
	
	@ExceptionHandler(value = {Exception.class})
	public ResponseEntity<Object> handleException(Exception e){
		CardResponse cardResponse = new CardResponse();
		cardResponse.setErrorMessage(e.getMessage());
		return new ResponseEntity<Object>(cardResponse,HttpStatus.INTERNAL_SERVER_ERROR);
	}

}

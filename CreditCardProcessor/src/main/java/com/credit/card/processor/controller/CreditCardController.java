package com.credit.card.processor.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.credit.card.processor.exception.InvalidInputException;
import com.credit.card.processor.pojo.CardDetails;
import com.credit.card.processor.pojo.CardResponse;
import com.credit.card.processor.service.CreditCardService;


@RestController
public class CreditCardController {
	
	@Autowired
	CreditCardService creditCardService;
	
	public void setCreditCardService(CreditCardService creditCardService) {
		this.creditCardService = creditCardService;
	}

	/**
	 * @param cardDetails
	 * @return ResponseEntity
	 * 
	 * RestController method for adding card in the DB
	 */
	@PostMapping(value = "/addCard" , consumes = "application/json" , produces = "application/json")
	public ResponseEntity<CardResponse> addCard(@RequestBody CardDetails cardDetails){
		
		if(cardDetails == null || cardDetails.getCardNumber() == null || cardDetails.getCardName() ==null || cardDetails.getLimit() == null) {
			throw new InvalidInputException("Card Details Missing or Empty. Check your Mandatory Parameters");
		}
		
		String cardNumber = cardDetails.getCardNumber();
		CardResponse cardResponse = new CardResponse();
		if(creditCardService.isValidCreditCardForLuhnValidation(cardNumber)) {
			creditCardService.addCard(cardDetails);
			cardResponse.setCardStatus("Created"); 
			return new ResponseEntity<CardResponse>(cardResponse,HttpStatus.CREATED);
		}else {
			throw new InvalidInputException("Card Number is not Valid as per Luhn's Algorithm"); 
		}
		
	}
	
	/**
	 * Method for fetching the stored card details
	 */
	@GetMapping(value = "/getAllCards" , consumes = "application/json" , produces = "application/json")
	public ResponseEntity<List<CardDetails>> getAllCards(){
		List<CardDetails> cardList = creditCardService.getAllCards();
		return new ResponseEntity<List<CardDetails>>(cardList,HttpStatus.OK);
		
	}
	
	
	

}

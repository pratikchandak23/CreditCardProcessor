package com.credit.card.processor.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.easymock.EasyMock;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import com.credit.card.processor.exception.InvalidInputException;
import com.credit.card.processor.pojo.CardDetails;
import com.credit.card.processor.pojo.CardResponse;
import com.credit.card.processor.service.CreditCardService;

public class CreditCardControllerTest {
	
	private CreditCardController creditCardController;
	private CardDetails cardDetails;
	private CreditCardService mockCreditCardService;
	private List<CardDetails> cardDetailsList ;
	
	@BeforeEach
	public void setUp() {
		creditCardController = new CreditCardController();
		cardDetails = new CardDetails();
		mockCreditCardService = EasyMock.createMock(CreditCardService.class);
		cardDetailsList = new ArrayList<CardDetails>();
		
		
	 }
	
	 @Test
	 public void testSuccessForAddCardWhenCardIsValid() {
		 mockCreditCardService(true);
		 cardDetails = getCardDetails(cardDetails);
		 
		 ResponseEntity<CardResponse> response = creditCardController.addCard(cardDetails);
		 Assertions.assertEquals("Created",response.getBody().getCardStatus());
		 Assertions.assertNull(response.getBody().getErrorMessage());
		 

	 }
	 
	 @Test
	 public void testAddCardWhenCardIsNotValid() {
		 mockCreditCardService(false);
		 cardDetails = getCardDetails(cardDetails);
		 
		 
		 try {
			 ResponseEntity<CardResponse> response = creditCardController.addCard(cardDetails); 
		 }catch(InvalidInputException ex) {
			 //Catching the exception only, meaning things have worked as expected.If exception not caught, then test case failure 
			 Assertions.assertEquals("Card Number is not Valid as per Luhn's Algorithm",ex.getMessage());
			 return;
		 }
		 
		 Assertions.fail();
	 }
	 
	 @Test
	 public void testSuccessForGetAllCards() {
		 mockCreditCardService(true);
		 cardDetails = getCardDetails(cardDetails);
		 cardDetailsList.add(cardDetails);
		 ResponseEntity<List<CardDetails>> cardListResponse = creditCardController.getAllCards();
		 Assertions.assertEquals(1,cardListResponse.getBody().size());
		 

	 }
	 
	 private void mockCreditCardService(boolean isCardValid) {
		 EasyMock.expect(mockCreditCardService.isValidCreditCardForLuhnValidation(EasyMock.anyString())).andReturn(isCardValid);
		 
		 EasyMock.expect(mockCreditCardService.getAllCards()).andReturn(cardDetailsList);
		 mockCreditCardService.addCard(cardDetails);
		 EasyMock.expectLastCall();
		 EasyMock.replay(mockCreditCardService);
		 creditCardController.setCreditCardService(mockCreditCardService);
	 }
	
	 private CardDetails getCardDetails(CardDetails cardDetails) {
		 String cardNumber = "4650 7628 1524 7309";
		 String cardName = "ABC";
		 BigDecimal balance = new BigDecimal(0);
		 BigDecimal limit = new BigDecimal(1000);
		 cardDetails.setCardNumber(cardNumber);
		 cardDetails.setBalance(balance);
		 cardDetails.setLimit(limit);
		 cardDetails.setCardName(cardName);
		 return cardDetails;
	 }
	 
	 
	 @AfterEach
	 public void tearDown() {
		 creditCardController = null;
	 }

}

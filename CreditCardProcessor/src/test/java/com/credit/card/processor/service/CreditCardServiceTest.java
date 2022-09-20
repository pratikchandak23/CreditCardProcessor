package com.credit.card.processor.service;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.credit.card.processor.exception.InvalidInputException;

public class CreditCardServiceTest {
	
	 private CreditCardServiceImpl creditCardService;
	 
	 
	 
	 @BeforeEach
	 public void setUp() {
		 creditCardService = new CreditCardServiceImpl();
		 
	 }
	 
	 @Test
	 public void testWhenValidCardNumber() {
		 String cardNumber = "4650762815247309";
		 boolean isCardValid = creditCardService.isValidCreditCardForLuhnValidation(cardNumber);
		 Assertions.assertEquals(true,isCardValid);
	 }
	 
	 @Test
	 public void testWhenInvalidCardNumber() {
		 String cardNumber = "1122334455667788";
		 boolean isCardValid = creditCardService.isValidCreditCardForLuhnValidation(cardNumber);
		 Assertions.assertEquals(false,isCardValid);
	 }
	 
	 @Test
	 public void testWhenCardNumberHasSpaces() {
		 String cardNumber = "4650 7628 1524 7309";
		 boolean isCardValid = creditCardService.isValidCreditCardForLuhnValidation(cardNumber);
		 Assertions.assertEquals(true,isCardValid);
	 }
	 
	 @Test
	 public void testWhenCardNumberLengthIsMoreThan19() {
		 String cardNumber = "46507628152473091234";
		 try {
			 boolean isCardValid = creditCardService.isValidCreditCardForLuhnValidation(cardNumber); 
		 }catch(InvalidInputException ex) {
			 //Catching the exception only, meaning things have worked as expected.If exception not caught, then test case failure 
			 Assertions.assertEquals("Card Number length is more than 19 digits",ex.getMessage());
			 return;
		 }
		 
		 Assertions.fail();
	 }
	 
	 
	 @Test
	 public void testWhenCardNumberisNonNumeric() {
		 String cardNumber = "TestCardNumber";
		 try {
			 boolean isCardValid = creditCardService.isValidCreditCardForLuhnValidation(cardNumber); 
		 }catch(InvalidInputException ex) {
			 //Catching the exception only, meaning things have worked as expected.If exception not caught, then test case failure 
			 Assertions.assertEquals("Card Number is not numeric",ex.getMessage());
			 return;
		 }
		 
		 Assertions.fail();
	 }
	 
	
	 
	 
	 
	 @AfterEach
	 public void tearDown() {
		 creditCardService = null;
	 }

}

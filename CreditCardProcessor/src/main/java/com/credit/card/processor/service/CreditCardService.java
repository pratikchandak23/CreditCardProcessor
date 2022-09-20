package com.credit.card.processor.service;

import java.util.List;

import com.credit.card.processor.pojo.CardDetails;

public interface CreditCardService {
	public boolean isValidCreditCardForLuhnValidation(String cardNo);
	public void addCard(CardDetails cardDetails);
	public List<CardDetails> getAllCards();
}

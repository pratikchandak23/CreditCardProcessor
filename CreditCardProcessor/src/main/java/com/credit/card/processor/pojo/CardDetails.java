package com.credit.card.processor.pojo;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;



@Entity(name="CARDDETAILS")
public class CardDetails {
	@Column(name="CARDNUMBER")
	@Id
	private String cardNumber;
	
	@Column(name="BALANCE")
	private BigDecimal balance = new BigDecimal(0);
	
	@Column(name="LIMITS")
	private BigDecimal limit;
	
	@Column(name="CARDNAME")
	private String cardName;
	
	public String getCardNumber() {
		return cardNumber;
	}
	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}
	public BigDecimal getBalance() {
		return balance;
	}
	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
	
	public BigDecimal getLimit() {
		return limit;
	}
	public void setLimit(BigDecimal limit) {
		this.limit = limit;
	}
	public String getCardName() {
		return cardName;
	}
	public void setCardName(String cardName) {
		this.cardName = cardName;
	}
	
	
	
}

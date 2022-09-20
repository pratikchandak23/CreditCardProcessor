package com.credit.card.processor.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.credit.card.processor.exception.InvalidInputException;
import com.credit.card.processor.pojo.CardDetails;
import com.credit.card.processor.repository.CardProcessingRepository;

@Service
public class CreditCardServiceImpl implements CreditCardService{
	
	@Autowired
	private CardProcessingRepository cardProcessingRepository;
	
	public void setCardProcessingRepository(CardProcessingRepository cardProcessingRepository) {
		this.cardProcessingRepository = cardProcessingRepository;
	}

	public boolean isValidCreditCardForLuhnValidation(String cardNo)
    {
        String cardNumber = cardNo.replaceAll(" ","");
        if(cardNumber != null && cardNumber.length() > 19) {
        	throw new InvalidInputException("Card Number length is more than 19 digits");
        }
        
		int[] cardIntArray=new int[cardNumber.length()];
		try {
			for(int i=0;i<cardNumber.length();i++)
	        {
	            char c= cardNumber.charAt(i);
	            cardIntArray[i]=  Integer.parseInt(""+c);
	        }
	 
	        for(int i=cardIntArray.length-2; i>=0; i=i-2)
	        {
	            int num = cardIntArray[i];
	            num = num * 2;  // step 1

	            if(num>9)
	            {
	                num = num%10 + num/10;  // step 2
	            }
	            cardIntArray[i]=num;
	        }
		}catch(NumberFormatException e) {
			throw new InvalidInputException("Card Number is not numeric");
		}
 
        
 
        int sum = sumDigits(cardIntArray);  // step 3
 
        if(sum%10==0)  // step 4
        {
            return true;
        }
 
        return false;
 
    }
	
	public void addCard(CardDetails cardDetails) {
		cardProcessingRepository.save(cardDetails);
	}
	
	public List<CardDetails> getAllCards() {
		return (List<CardDetails>) cardProcessingRepository.findAll();
	}
 
    public static int sumDigits(int[] arr)
    {
        return Arrays.stream(arr).sum();
    }

}

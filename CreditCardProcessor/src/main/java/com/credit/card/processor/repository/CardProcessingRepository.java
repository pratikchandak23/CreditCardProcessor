package com.credit.card.processor.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.credit.card.processor.pojo.CardDetails;

@Repository
public interface CardProcessingRepository extends CrudRepository<CardDetails,String>{
	

}

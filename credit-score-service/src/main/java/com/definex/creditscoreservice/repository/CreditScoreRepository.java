package com.definex.creditscoreservice.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.definex.creditscoreservice.model.CreditScore;

public interface CreditScoreRepository extends JpaRepository<CreditScore, Long>{

	CreditScore findByIdentityNo(String identityNo);
	
	boolean existsByIdentityNo(String identityNo);

}

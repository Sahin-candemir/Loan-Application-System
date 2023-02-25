package com.definex.creditscoreservice.service;

import com.definex.creditscoreservice.dto.CreditScoreDto;
import com.definex.creditscoreservice.dto.CreditScoreRequest;

public interface CreditScoreService {

	CreditScoreDto createCreditScore(CreditScoreDto creditScoreDto);

	CreditScoreDto getCreditScoreByIdentityNo(CreditScoreRequest creditScoreRequest);

}

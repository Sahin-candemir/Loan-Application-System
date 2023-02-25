package com.definex.creditscoreservice.serviceImpl;

import org.springframework.stereotype.Service;

import com.definex.creditscoreservice.dto.CreditScoreDto;
import com.definex.creditscoreservice.dto.CreditScoreRequest;
import com.definex.creditscoreservice.model.CreditScore;
import com.definex.creditscoreservice.repository.CreditScoreRepository;
import com.definex.creditscoreservice.service.CreditScoreService;

@Service
public class CreditScoreServiceImpl implements CreditScoreService{

	private final CreditScoreRepository creditScoreRepository;
	
	public CreditScoreServiceImpl(CreditScoreRepository creditScoreRepository) {
		this.creditScoreRepository = creditScoreRepository;
	}

	@Override
	public CreditScoreDto createCreditScore(CreditScoreDto creditScoreDto) {
		if(creditScoreRepository.existsByIdentityNo(creditScoreDto.getIdentityNo())) {
			CreditScore creditScore =creditScoreRepository.findByIdentityNo(creditScoreDto.getIdentityNo());
			creditScore.setCreditScore(creditScoreDto.getCreditScore());
			creditScoreRepository.save(creditScore);
			return creditScoreDto;
		}
			
		CreditScore creditScore = CreditScore.builder()
				.creditScore(creditScoreDto.getCreditScore())
				.identityNo(creditScoreDto.getIdentityNo())
				.build();
		
		creditScoreRepository.save(creditScore);
		return creditScoreDto;
	}

	@Override
	public CreditScoreDto getCreditScoreByIdentityNo(CreditScoreRequest creditScoreRequest) {
		CreditScore creditScore = creditScoreRepository.findByIdentityNo(creditScoreRequest.getIdentityNo());
		CreditScoreDto creditScoreDto = CreditScoreDto.builder()
				.creditScore(creditScore.getCreditScore())
				.identityNo(creditScore.getIdentityNo())
				.build();
		return creditScoreDto;
	}

	
}

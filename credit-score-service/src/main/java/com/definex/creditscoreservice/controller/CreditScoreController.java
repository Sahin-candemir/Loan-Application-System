package com.definex.creditscoreservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.definex.creditscoreservice.dto.CreditScoreDto;
import com.definex.creditscoreservice.dto.CreditScoreRequest;
import com.definex.creditscoreservice.service.CreditScoreService;

@RestController
@RequestMapping("/api/credit-score")
public class CreditScoreController {

	private final CreditScoreService creditScoreService;
	
	public CreditScoreController(CreditScoreService creditScoreService) {

		this.creditScoreService = creditScoreService;
	}

	@PostMapping("/create")
	public ResponseEntity<CreditScoreDto> createCreditScore(@RequestBody CreditScoreDto creditScoreDto){
		;
		return new ResponseEntity<>(creditScoreService.createCreditScore(creditScoreDto), HttpStatus.CREATED);
	}
	
	
	@PostMapping
	public ResponseEntity<CreditScoreDto> getCreditScoreByIdentityNo(@RequestBody CreditScoreRequest creditScoreRequest){
		return new ResponseEntity<>(creditScoreService.getCreditScoreByIdentityNo(creditScoreRequest),HttpStatus.OK);
	}
	
	
}

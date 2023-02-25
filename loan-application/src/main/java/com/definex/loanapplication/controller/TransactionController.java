package com.definex.loanapplication.controller;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.definex.loanapplication.dto.LoanApplicationRequest;
import com.definex.loanapplication.dto.LoanApplicationResponse;
import com.definex.loanapplication.dto.LoanApplicationTranckingRequest;
import com.definex.loanapplication.service.TransactionService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/transaction")
public class TransactionController {

	private final TransactionService transactionService;

	public TransactionController(TransactionService transactionService) {
		this.transactionService = transactionService;
	}

	@CrossOrigin
	@PostMapping
	public ResponseEntity<LoanApplicationResponse> loanApplication(@Valid @RequestBody LoanApplicationRequest loanApplicationRequest){
		return new ResponseEntity<>(transactionService.loanApplication(loanApplicationRequest),HttpStatus.OK);
	} 
	@PostMapping("/tracking")
	public ResponseEntity<LoanApplicationResponse> LoanApplicationTracking(@RequestBody LoanApplicationTranckingRequest applicationTranckingRequest){
		return new ResponseEntity<>(transactionService.LoanApplicationTracking(applicationTranckingRequest),HttpStatus.OK);
	}
	
}

package com.definex.loanapplication.serviceImpl;

import java.math.BigDecimal;
import java.util.Date;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.definex.loanapplication.dto.CreditScoreRequest;
import com.definex.loanapplication.dto.CreditScoreResponse;
import com.definex.loanapplication.dto.LoanApplicationRequest;
import com.definex.loanapplication.dto.LoanApplicationResponse;
import com.definex.loanapplication.dto.LoanApplicationTranckingRequest;
import com.definex.loanapplication.exception.IdentityNoOrPhoneNumberAllreadyExists;
import com.definex.loanapplication.model.Customer;
import com.definex.loanapplication.model.Formula;
import com.definex.loanapplication.model.Transaction;

import com.definex.loanapplication.repository.TransactionRepository;
import com.definex.loanapplication.service.CustomerService;
import com.definex.loanapplication.service.FormulaService;
import com.definex.loanapplication.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService{

	
	private final static int creditLimitMultiplier = 4;

	private final CustomerService customerService;
	
	private final FormulaService formulaService;
	
	private final TransactionRepository transactionRepository;
	
	public TransactionServiceImpl(TransactionRepository transactionRepository, FormulaService formulaService, CustomerService customerService ) {
		this.customerService = customerService;
		this.formulaService = formulaService;
		this.transactionRepository = transactionRepository;
	}

	
	@Override
	public LoanApplicationResponse loanApplication(LoanApplicationRequest loanApplicationRequest) {

		Customer customer = customerService.getCustomerByIdentityNo(loanApplicationRequest.getIdentityNo());
		customerService.validateCustomer(loanApplicationRequest);

		if(transactionRepository.findByCustomerIdFirstByOrderByCreatedDate(customer.getId())!=null && transactionRepository.findByCustomerIdFirstByOrderByCreatedDate(customer.getId()).getLoanApplicationResult())
			throw new IdentityNoOrPhoneNumberAllreadyExists("Customer has an approved loan application");
		
		RestTemplate restTemplate = new RestTemplate();
		LoanApplicationResponse loanApplicationResponse = new LoanApplicationResponse();
		
		//Body to send to credit-score-service
		CreditScoreRequest creditScoreRequest = CreditScoreRequest.builder()
				.identityNo(loanApplicationRequest.getIdentityNo())
				.build();
	
			//Receives credit score with submitted creditScoreRequest
			int creditScore = restTemplate
					.postForObject("http://localhost:8082/api/credit-score", creditScoreRequest, CreditScoreResponse.class)
					.getCreditScore();
			//Receives formula details on credit score and monthly salary from formula table
			Formula formula = formulaService.getFormulaByCreditScoreAndMonthlySalary(creditScore, loanApplicationRequest.getMonthlySalary());
			
			//if the credit result is false in the returned formula
			if(!formula.getCreditResult()) {
				
				loanApplicationResponse.setCreditLimit(null);
				loanApplicationResponse.setResult(formula.getCreditResult().toString());
			}
			
			/*
			 * monthlySalary*(creditLimitMultiplier*rateCreditLimitMultiplier) + deposit*rateDeposit
			 * */
			if(formula.getAmount()==null) {

				BigDecimal creditLimit = loanApplicationRequest.getMonthlySalary()
						.multiply(BigDecimal.valueOf(creditLimitMultiplier*formula.getRateCreditLimitMultiplier()));
				
				creditLimit =creditLimit.add(loanApplicationRequest.getDeposit().multiply(BigDecimal.valueOf(formula.getRateDeposit())));
				
					loanApplicationResponse.setCreditLimit(creditLimit);
					loanApplicationResponse.setResult(formula.getCreditResult().toString());
			}
			/*
			 * amount+(deposit*rateDeposit)
			 * */
			else {
				BigDecimal creditLimit = formula.getAmount().add(loanApplicationRequest.getDeposit().multiply(BigDecimal.valueOf(formula.getRateDeposit())));
				
				loanApplicationResponse.setCreditLimit(creditLimit);
				loanApplicationResponse.setResult(formula.getCreditResult().toString());
			}
			
			Date date = new Date();
			Transaction transaction = Transaction.builder()
					.creditLimit(loanApplicationResponse.getCreditLimit())
					.loanApplicationResult(formula.getCreditResult())
					.customer(customer)
					.createdDate(date)
					.build();
			transactionRepository.save(transaction);
			
			return loanApplicationResponse;
			
	}

	// Loan application result tracking with identity number and birth date information
	@Override
	public LoanApplicationResponse LoanApplicationTracking(LoanApplicationTranckingRequest applicationTranckingRequest) {
		//Compares the information identity number and birth date with existing customer information
		Customer customer = customerService
				.getCustomerByIdentityNoAndBirthDate(applicationTranckingRequest.getIdentityNo(), applicationTranckingRequest.getBirthDate());
		
		//Receives last recorded transaction with customer id
		Transaction transaction = transactionRepository
				.findByCustomerIdFirstByOrderByCreatedDate(customer.getId());
		
		LoanApplicationResponse loanApplicationResponse = new LoanApplicationResponse();
		
		loanApplicationResponse.setCreditLimit(transaction.getCreditLimit());
		loanApplicationResponse.setResult(transaction.getLoanApplicationResult().toString());
		
		return loanApplicationResponse;
	}

}

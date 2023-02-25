package com.definex.loanapplication.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
import java.util.Date;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.definex.loanapplication.dto.LoanApplicationRequest;
import com.definex.loanapplication.model.Customer;
import com.definex.loanapplication.repository.TransactionRepository;
import com.definex.loanapplication.service.CustomerService;
import com.definex.loanapplication.service.FormulaService;

@ExtendWith(MockitoExtension.class)
public class TransactionServiceImplTest {

	@InjectMocks
	private TransactionServiceImpl transactionServiceImpl;
	@Mock
	private TransactionRepository transactionRepository;
	@Mock
	private FormulaService formulaService;
	@Mock
	private CustomerService customerService;
	
	void loanApplication() {
		Date date = new Date();
		LoanApplicationRequest loanApplicationRequest = new LoanApplicationRequest();
		loanApplicationRequest.setIdentityNo("34916274040");
		loanApplicationRequest.setName("Şahin");
		loanApplicationRequest.setSurname("Candemir");
		loanApplicationRequest.setPhoneNumber("5541902065");
		loanApplicationRequest.setMonthlySalary(BigDecimal.valueOf(9000.00));
		loanApplicationRequest.setBirthDate(date);
		loanApplicationRequest.setDeposit(BigDecimal.valueOf(2000.00));
		
		Customer customer = customerService.getCustomerByIdentityNo("34916274040");
		
		int creditScore = 950;
	}
	
}

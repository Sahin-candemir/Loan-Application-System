package com.definex.loanapplication.service;

import java.util.Date;

import com.definex.loanapplication.dto.CustomerDto;
import com.definex.loanapplication.dto.LoanApplicationRequest;
import com.definex.loanapplication.dto.updateCustomerDto;
import com.definex.loanapplication.model.Customer;

public interface CustomerService {

	CustomerDto createCustomer(CustomerDto customerDto);

	CustomerDto updateCustomer(Long id, updateCustomerDto updateCustomerDto);

	void deleteCustomer(Long id);
	
	Customer getCustomerByIdentityNo(String identityNo);

	Customer getCustomerByIdentityNoAndBirthDate(String identityNo, Date birthDate);

	void validateCustomer(LoanApplicationRequest loanApplicationRequest);
}

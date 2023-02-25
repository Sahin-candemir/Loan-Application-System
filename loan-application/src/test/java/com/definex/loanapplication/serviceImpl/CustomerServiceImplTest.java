package com.definex.loanapplication.serviceImpl;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mockitoSession;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import com.definex.loanapplication.dto.CustomerDto;
import com.definex.loanapplication.model.Customer;
import com.definex.loanapplication.repository.CustomerRepository;
import com.definex.loanapplication.service.CustomerService;

@ExtendWith(MockitoExtension.class)
public class CustomerServiceImplTest {

	@InjectMocks
	private CustomerServiceImpl customerServiceImpl;
	@Mock
	private CustomerRepository customerRepository;
	@Mock
	private ModelMapper modelMapper;
	
	
	@Test
	void shouldCreateCustomerWithCustomerDto_whenIdentityNoAndPhoneNumberNotExists() {
		Date date = new Date();
		CustomerDto customerDto = new CustomerDto();
		customerDto.setName("sahin");
		customerDto.setSurname("candemir");
		customerDto.setIdentityNo("12345678989");
		customerDto.setMonthlySalary(BigDecimal.valueOf(5000));
		customerDto.setPhoneNumber("5541902065");
		customerDto.setBirthDate(date);
		
		CustomerDto result = customerServiceImpl.createCustomer(customerDto);
		
		Assertions.assertEquals(result, customerDto);
	}
	@Test
	void shouldCreateCustomerWithCustomerDto_whenIdentityNoAndPhoneNumberAllreadyExists() {
		Date date = new Date();
		CustomerDto customerDto = new CustomerDto("12345678989","sahin","candemir","5541902065",BigDecimal.valueOf(5000),date);
	
	
	}
	
}

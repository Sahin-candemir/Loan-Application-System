package com.definex.loanapplication.serviceImpl;

import java.util.Date;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.definex.loanapplication.dto.CreditScoreDto;
import com.definex.loanapplication.dto.CustomerDto;
import com.definex.loanapplication.dto.LoanApplicationRequest;
import com.definex.loanapplication.dto.updateCustomerDto;
import com.definex.loanapplication.exception.IdentityNoOrPhoneNumberAllreadyExists;
import com.definex.loanapplication.exception.ResourceNotFoundException;
import com.definex.loanapplication.model.Customer;
import com.definex.loanapplication.repository.CustomerRepository;
import com.definex.loanapplication.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CustomerServiceImpl implements CustomerService{

	private final CustomerRepository customerRepository;
	
	private final ModelMapper modelMapper;
	
	public CustomerServiceImpl(CustomerRepository customerRepository, ModelMapper modelMapper) {
		this.customerRepository = customerRepository;
		this.modelMapper = modelMapper;
	}

	@Override
	public CustomerDto createCustomer(CustomerDto customerDto) {
		Date dateNow = new Date();
		if(customerRepository.existsByIdentityNoOrPhoneNumber(customerDto.getIdentityNo(), customerDto.getPhoneNumber()))
			throw new IdentityNoOrPhoneNumberAllreadyExists("Identity No or phone number allready exists.");
		
		Customer customer = mapToEntity(customerDto);
				customer.setCreatedDate(dateNow);
		customerRepository.save(customer);
		
		createCustomerCreditScoreWithRestTemplate(customerDto.getIdentityNo());
		
		return mapToDto(customer);
	}

	@Override
	public CustomerDto updateCustomer(Long id, updateCustomerDto updateCustomerDto) {
		Customer customer = customerRepository.findById(id).
				orElseThrow(()-> new ResourceNotFoundException ("User not found with id"+id));
		
		customer.setName(updateCustomerDto.getName());
		customer.setSurname(updateCustomerDto.getSurname());
		customer.setBirthDate(updateCustomerDto.getBirthDate());
		customer.setPhoneNumber(updateCustomerDto.getPhoneNumber());
		customer.setMonthlySalary(updateCustomerDto.getMonthlySalary());
		
		customerRepository.save(customer);
		
		return mapToDto(customer);
	}

	@Override
	public void deleteCustomer(Long id) {
		Customer customer = customerRepository.findById(id)
				.orElseThrow(()-> new ResourceNotFoundException ("User not found with id"+id));
		
		customerRepository.delete(customer);
	}

	//Used in transacation service
	@Override
	public Customer getCustomerByIdentityNo(String identityNo) {
		Customer customer = customerRepository.findByIdentityNo(identityNo).orElseThrow(()-> new ResourceNotFoundException("Customer no found with identity no."));
		return customer;
	}

	//Used in transaction service
	@Override
	public Customer getCustomerByIdentityNoAndBirthDate(String identityNo, Date birthDate) {
		Customer customer = customerRepository.findByIdentityNoAndBirthDate(identityNo, birthDate).orElseThrow(()-> new ResourceNotFoundException("Customer not found with identity no or birthDate"));
		return customer;
	}
	//Assigns a random value to credit-score-service for the customer created in the database
	public void createCustomerCreditScoreWithRestTemplate(String identityNo) {
		RestTemplate restTemplate = new RestTemplate();
		CreditScoreDto creditScoreDto = new CreditScoreDto();
		creditScoreDto.setCreditScore(0+(int) (Math.random()*(1901)+0));
		creditScoreDto.setIdentityNo(identityNo);
		int creditScore = restTemplate.postForObject("http://localhost:8082/api/credit-score/create", creditScoreDto, CreditScoreDto.class).getCreditScore();
		log.info("Customer credit Score created succesfully! Credit Score : "+creditScore);
	}

	//Compares the information in the loan application request with existing customer information
	@Override
	public void validateCustomer(LoanApplicationRequest loanApplicationRequest) {
		Customer customer = customerRepository.findByIdentityNo(loanApplicationRequest.getIdentityNo()).orElseThrow(()-> new ResourceNotFoundException("Customer not found with identity no."));
		if(!modelMapper.map(loanApplicationRequest, CustomerDto.class).equals(modelMapper.map(customer, CustomerDto.class)))
			throw new ResourceNotFoundException("Invalid Request.");
	}
	
	public Customer mapToEntity(CustomerDto customerDto) {
		Customer customer = new Customer();
		
		customer.setName(customerDto.getName());
		customer.setSurname(customerDto.getSurname());
		customer.setBirthDate(customerDto.getBirthDate());
		customer.setIdentityNo(customerDto.getIdentityNo());
		customer.setPhoneNumber(customerDto.getPhoneNumber());
		customer.setMonthlySalary(customerDto.getMonthlySalary());
		
		return customer;
	}
	public CustomerDto mapToDto(Customer customer) {
		CustomerDto customerDto = new CustomerDto();
		
		customerDto.setName(customer.getName());
		customerDto.setSurname(customer.getSurname());
		customerDto.setBirthDate(customer.getBirthDate());
		customerDto.setIdentityNo(customer.getIdentityNo());
		customerDto.setPhoneNumber(customer.getPhoneNumber());
		customerDto.setMonthlySalary(customer.getMonthlySalary());
		
		return customerDto;
	}
}

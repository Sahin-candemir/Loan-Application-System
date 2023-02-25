package com.definex.loanapplication.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.definex.loanapplication.dto.CustomerDto;
import com.definex.loanapplication.dto.updateCustomerDto;
import com.definex.loanapplication.service.CustomerService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

	private final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@PostMapping
	public ResponseEntity<CustomerDto> createCustomer(@Valid @RequestBody CustomerDto customerDto){
		return new ResponseEntity<>(customerService.createCustomer(customerDto), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<CustomerDto> updateCustomer(@PathVariable Long id,@Valid  @RequestBody updateCustomerDto updateCustomerDto){
		return new ResponseEntity<>(customerService.updateCustomer(id, updateCustomerDto), HttpStatus.OK);
	}
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteCustomer(@PathVariable Long id){
		customerService.deleteCustomer(id);
		return new ResponseEntity<>("Customer deleted Successfully.", HttpStatus.OK);
	}
}

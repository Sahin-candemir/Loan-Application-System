package com.definex.loanapplication.repository;

import java.util.Date;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.definex.loanapplication.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long>{

	Optional<Customer> findByIdentityNo(String identityNo);

	boolean existsByIdentityNoOrPhoneNumber(String identityNo, String phoneNumber);
	
	Optional<Customer> findByIdentityNoAndBirthDate(String identityNo, Date birthDate);
}

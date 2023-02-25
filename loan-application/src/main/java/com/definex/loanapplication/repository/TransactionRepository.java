package com.definex.loanapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.definex.loanapplication.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

	@Query(value="SELECT * FROM transaction t WHERE ?1=t.customer_id ORDER BY t.created_date DESC LIMIT 1", nativeQuery = true)
	Transaction findByCustomerIdFirstByOrderByCreatedDate(Long customerId);

}

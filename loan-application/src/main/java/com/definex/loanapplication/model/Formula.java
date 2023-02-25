package com.definex.loanapplication.model;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table
@Entity
public class Formula {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer minCreditScore;
	
	private Integer maxCreditScore;
	
	private BigDecimal minSalary;
	
	private BigDecimal maxSalary;
	
	private BigDecimal amount;
	
	private float rateCreditLimitMultiplier;
	
	private double rateDeposit;
	
	private Boolean creditResult;
}

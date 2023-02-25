package com.definex.loanapplication.repository;

import java.math.BigDecimal;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.definex.loanapplication.model.Formula;

public interface FormulaRepository extends JpaRepository<Formula, Long>{

	@Query(value = "SELECT * FROM formula d WHERE ?1 BETWEEN d.min_credit_score AND d.max_credit_score AND ?2 BETWEEN d.min_salary AND d.max_salary",
			nativeQuery = true)
	Formula findFormulaByCreditScoreAndMonthlySalary(Integer creditScore, BigDecimal monthlySalary);
}

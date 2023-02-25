package com.definex.loanapplication.serviceImpl;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import com.definex.loanapplication.model.Formula;
import com.definex.loanapplication.repository.FormulaRepository;
import com.definex.loanapplication.service.FormulaService;

@Service
public class FormulaServiceImpl implements FormulaService{

	
	private final FormulaRepository formulaRepository;
	
	public FormulaServiceImpl(FormulaRepository formulaRepository) {
		this.formulaRepository = formulaRepository;
	}

	@Override
	public Formula getFormulaByCreditScoreAndMonthlySalary(Integer creditScore, BigDecimal monthlySalary) {
		Formula formula = formulaRepository
				.findFormulaByCreditScoreAndMonthlySalary(creditScore, monthlySalary);
		return formula;
	}

}

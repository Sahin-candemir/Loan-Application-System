package com.definex.loanapplication.service;

import java.math.BigDecimal;

import com.definex.loanapplication.model.Formula;

public interface FormulaService {

	Formula getFormulaByCreditScoreAndMonthlySalary(Integer creditScore, BigDecimal monthlySalary);
}

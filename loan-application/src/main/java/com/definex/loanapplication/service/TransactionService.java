package com.definex.loanapplication.service;

import com.definex.loanapplication.dto.LoanApplicationRequest;
import com.definex.loanapplication.dto.LoanApplicationResponse;
import com.definex.loanapplication.dto.LoanApplicationTranckingRequest;

public interface TransactionService {

	LoanApplicationResponse loanApplication(LoanApplicationRequest loanApplicationRequest);

	LoanApplicationResponse LoanApplicationTracking(LoanApplicationTranckingRequest applicationTranckingRequest);

}

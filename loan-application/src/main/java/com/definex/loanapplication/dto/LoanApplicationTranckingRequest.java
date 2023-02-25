package com.definex.loanapplication.dto;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoanApplicationTranckingRequest {

	private String identityNo;
	
	private Date birthDate;
}

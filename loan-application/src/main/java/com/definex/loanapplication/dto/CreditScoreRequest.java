package com.definex.loanapplication.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class CreditScoreRequest {

	private String identityNo;
}

package com.definex.creditscoreservice.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class CreditScoreDto {

	@Min(value=0, message="sdfsd")
	@Max(value=1900, message="asdfasdf")
	private int creditScore;
	@Size(min = 11, max = 11, message = "Identity length must be 11")
	@NotEmpty
	@NotNull
	private String identityNo;

}

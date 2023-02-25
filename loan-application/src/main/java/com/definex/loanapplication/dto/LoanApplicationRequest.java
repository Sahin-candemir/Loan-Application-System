package com.definex.loanapplication.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoanApplicationRequest {

	@Pattern(regexp = "^\\d{11}$", message = "Invalid identity number.")
	@NotEmpty(message = "Identity number may not be empty.")
    @NotNull(message = "Identity number may not be null.")
	private String identityNo;
	
	@NotEmpty(message = "Customer name may not be empty.")
    @NotNull(message = "Customer name may not be null.")
	@Size(min = 3, max = 30, message = "Customer name Must be of 3 - 30 characters")
	private String name;

	@NotEmpty(message = "Customer surname may not be empty.")
    @NotNull(message = "Customer surname may not be null.")
	@Size(min = 3, max = 30, message = "Customer surname Must be of 3 - 30 characters")
	private String surname;
	
	@NotEmpty(message = "Phone number may not be empty.")
    @NotNull(message = "Phone number may not be null.")
    @Pattern(regexp = "^\\d{10}$", message = "Invalid phone number")
	private String phoneNumber;
	
    @NotNull(message = "Montly salary may not be null.")
	private BigDecimal monthlySalary;
	
	@JsonFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Birth Date may not be null.")
	private Date birthDate;
	
	@Min(value = 0, message = "Deposit can not be negative")
	private BigDecimal deposit;
}

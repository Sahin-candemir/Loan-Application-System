package com.definex.loanapplication.dto;

import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class updateCustomerDto {

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
	
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date birthDate;

	@NotNull(message = "Montly salary may not be null.")
	private BigDecimal monthlySalary;

}

package com.ems.dto;

import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployeeDTO {

	private Long eid;

	@NotBlank(message = "Name is required")
	private String name;

	@NotBlank(message = "Email is required")
	@Email(message = "Invalid email format")
	private String email;

	@NotBlank(message = "Phone is required")
	@Pattern(regexp = "\\d{10}", message = "Phone must be exactly 10 digits")
	private String phone;

	@NotBlank(message = "Department is required")
	@Pattern(regexp = "^(?!.*\\d).*$", message = "Department can't be number")
	private String department;

	@NotNull(message = "Salary is required")
	@Positive(message = "Salary can't be negative")
	private Double salary;

}

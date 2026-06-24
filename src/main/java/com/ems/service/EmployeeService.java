package com.ems.service;

import java.util.List;

import com.ems.dto.EmployeeDTO;
import com.ems.entity.Employee;

import jakarta.validation.Valid;

public interface EmployeeService {

	EmployeeDTO saveEmployee(EmployeeDTO dto);

	List<EmployeeDTO> getAllEmployees();

	EmployeeDTO getEmployeeById(Long eid);

	EmployeeDTO updateEmployee(Long eid, @Valid EmployeeDTO dto);

	EmployeeDTO partialUpdateEmployee(Long eid, EmployeeDTO dto);

	void deleteEmployee(Long eid);

	List<EmployeeDTO> searchEmployees(String keyword);

}

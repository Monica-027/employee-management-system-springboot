package com.ems.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ems.dto.EmployeeDTO;
import com.ems.entity.Employee;
import com.ems.exception.ResourceNotFoundException;
import com.ems.repository.EmployeeRepository;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository repository;

	@Override
	public EmployeeDTO saveEmployee(EmployeeDTO dto) {

		Employee emp = mapToEntity(dto);

		return mapToDTO(repository.save(emp));
	}

	@Override
	public List<EmployeeDTO> getAllEmployees() {
		List<Employee> employees = repository.findAll();

		List<EmployeeDTO> dtoList = new ArrayList<>();

		for (Employee emp : employees) {
			dtoList.add(mapToDTO(emp));
		}
		return dtoList;
	}

	@Override
	public EmployeeDTO getEmployeeById(Long eid) {

		Employee emp = repository.findById(eid)
				.orElseThrow(() -> new ResourceNotFoundException("Employee with Eid: " + eid + " not found"));
		return mapToDTO(emp);
	}

	@Override
	public EmployeeDTO updateEmployee(Long eid, EmployeeDTO dto) {

		Employee existingEmp = repository.findById(eid)
				.orElseThrow(() -> new ResourceNotFoundException("Employee with Eid: " + eid + " not found"));

		existingEmp.setName(dto.getName());
		existingEmp.setEmail(dto.getEmail());
		existingEmp.setPhone(dto.getPhone());
		existingEmp.setDepartment(dto.getDepartment());
		existingEmp.setSalary(dto.getSalary());

		return mapToDTO(repository.save(existingEmp));
	}

	@Override
	public EmployeeDTO partialUpdateEmployee(Long eid, EmployeeDTO dto) {
		Employee existingEmp = repository.findById(eid)
				.orElseThrow(() -> new ResourceNotFoundException("Employee with Eid: " + eid + " not found"));

		// Update field values provided by user
		if (dto.getName() != null) {
			existingEmp.setName(dto.getName());
		}
		if (dto.getEmail() != null) {
			existingEmp.setEmail(dto.getEmail());
		}
		if (dto.getPhone() != null) {
			existingEmp.setPhone(dto.getPhone());
		}
		if (dto.getDepartment() != null) {
			existingEmp.setDepartment(dto.getDepartment());
		}
		if (dto.getSalary() != null) {
			existingEmp.setSalary(dto.getSalary());
		}
		return mapToDTO(repository.save(existingEmp));
	}

	@Override
	public void deleteEmployee(Long eid) {
		Employee emp = repository.findById(eid)
				.orElseThrow(() -> new ResourceNotFoundException("Employee with ID: " + eid + " not found"));

		repository.deleteById(eid);
	}

	// Search employee by name
	@Override
	public List<EmployeeDTO> searchEmployees(String keyword) {
		List<Employee> employees = repository.findByNameContainingIgnoreCase(keyword);
		List<EmployeeDTO> dtoList = new ArrayList<>();
		for (Employee emp : employees) {
			dtoList.add(mapToDTO(emp));
		}
		return dtoList;
	}

	// Map DTO to Entity
	private Employee mapToEntity(EmployeeDTO dto) {
		Employee emp = new Employee();
		emp.setName(dto.getName());
		emp.setEmail(dto.getEmail());
		emp.setPhone(dto.getPhone());
		emp.setDepartment(dto.getDepartment());
		emp.setSalary(dto.getSalary());
		return emp;
	}

	// Map Entity to DTO
	private EmployeeDTO mapToDTO(Employee emp) {
		EmployeeDTO dto = new EmployeeDTO();
		dto.setEid(emp.getEid());
		dto.setName(emp.getName());
		dto.setEmail(emp.getEmail());
		dto.setPhone(emp.getPhone());
		dto.setDepartment(emp.getDepartment());
		dto.setSalary(emp.getSalary());
		return dto;
	}

}

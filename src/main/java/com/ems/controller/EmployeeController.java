package com.ems.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ems.dto.EmployeeDTO;
import com.ems.service.EmployeeService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/employees")
public class EmployeeController {

	// Inject EmployeeService dependency
	@Autowired
	private EmployeeService service;

	@PostMapping
	public ResponseEntity<EmployeeDTO> saveEmployee(@RequestBody @Valid EmployeeDTO dto) {
		EmployeeDTO saved = service.saveEmployee(dto);
		return ResponseEntity.status(HttpStatus.CREATED).body(saved);
	}

	@GetMapping
	public List<EmployeeDTO> getAllEmployees() {
		return service.getAllEmployees();
	}

	@GetMapping("/{eid}")
	public EmployeeDTO getEmployeeById(@PathVariable Long eid) {
		return service.getEmployeeById(eid);
	}

	@PutMapping("/{eid}")
	public EmployeeDTO updateEmployee(@PathVariable Long eid, @RequestBody @Valid EmployeeDTO dto) {
		return service.updateEmployee(eid, dto);

	}

	// Update specific employee fields (partial update)
	@PatchMapping("/{eid}")
	public EmployeeDTO partialUpdateEmployee(@PathVariable Long eid, @RequestBody EmployeeDTO dto) {
		return service.partialUpdateEmployee(eid, dto);

	}

	@GetMapping("/search")
	public List<EmployeeDTO> searchEmployees(@RequestParam String keyword) {
		return service.searchEmployees(keyword);
	}

	@DeleteMapping("/{eid}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable Long eid) {
        service.deleteEmployee(eid);
        return ResponseEntity.noContent().build();
    }

}

package com.nxsol.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.nxsol.dto.EmployeeDto;
import com.nxsol.dto.ErrorDetails;
import com.nxsol.entity.Employee;
import com.nxsol.service.EmployeeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
	  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	      request.getDescription(false));
	  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	
	@GetMapping()
	public ResponseEntity<?> findAllEmployees() throws Exception{
		return new ResponseEntity<List<EmployeeDto>>(employeeService.findAllEmployee(), HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public EmployeeDto findEmployeeById(@PathVariable Long id) throws Exception{
		return employeeService.findEmployeeById(id);
	}
	
	@PostMapping()
	public ResponseEntity<?> addNewEmployee(@RequestBody EmployeeDto employeeDto) throws Exception {
		return new ResponseEntity<>(employeeService.addEmployee(employeeDto), HttpStatus.OK);
	}
	
	@PutMapping()
	public ResponseEntity<?> updateEmployee(@RequestBody EmployeeDto employeeDto) throws Exception {
		return new ResponseEntity<>(employeeService.updateEmployee(employeeDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteEmployee(@PathVariable Long id) throws Exception {
		employeeService.deleteEmployee(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
}

package com.nxsol.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nxsol.dto.EmployeeDto;
import com.nxsol.entity.Employee;
import com.nxsol.service.EmployeeService;

@RestController
@CrossOrigin("*")
@RequestMapping("/emp")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;
	
	@GetMapping()
	public List<Employee> findAllEmployees() throws Exception{
		return employeeService.findAllEmployee();
	}
	
	@GetMapping("/{id}")
	public Employee findEmployeeById(@PathVariable Long id) throws Exception{
		return employeeService.findEmployeeById(id);
	}
	
	@PostMapping()
	public Employee addNewEmployee(@RequestBody EmployeeDto employeeDto) throws Exception {
		return employeeService.addEmployee(employeeDto);	
	}
	
	@PutMapping()
	public Employee updateEmployee(@RequestBody EmployeeDto employeeDto) throws Exception {
		return employeeService.updateEmployee(employeeDto);	
	}
	
	@DeleteMapping("/{id}")
	public void deleteEmployee(@PathVariable Long id) throws Exception {
		employeeService.deleteEmployee(id);	
	}
	
	/*@GetMapping("/{empId}/{projId}")
	public List<Employee> findAllEmployeesWithProjectId(@PathVariable Long empId, @PathVariable Long projectId) throws Exception{
		return employeeService.findAllEmployeeByProjectId(empId, projectId);
	}*/
	
	
	

	
	
	
	
}

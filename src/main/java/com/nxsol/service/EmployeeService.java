package com.nxsol.service;

import java.util.List;

import com.nxsol.dto.EmployeeDto;
import com.nxsol.entity.Employee;

public interface EmployeeService {
	
	public List<Employee> findAllEmployee() throws Exception;
	
	public Employee findEmployeeById(Long id) throws Exception;
	
	public Employee addEmployee(EmployeeDto employeeDto) throws Exception;
	
	public Employee updateEmployee(EmployeeDto employeeDto) throws Exception;
	
	public Employee deleteEmployee(Long id) throws Exception;
	
	
	
	
	
	//public List<Employee> findAllEmployeeByProjectId(Long empId, Long projectId) throws Exception;
}

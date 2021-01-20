package com.nxsol.service;

import java.util.List;

import com.nxsol.dto.EmployeeDto;
import com.nxsol.entity.Employee;

public interface EmployeeService {
	
	public List<EmployeeDto> findAllEmployee() throws Exception;
	
	public EmployeeDto findEmployeeById(Long id) throws Exception;
	
	public Employee addEmployee(EmployeeDto employeeDto) throws Exception;
	
	public Employee updateEmployee(EmployeeDto employeeDto) throws Exception;
	
	public Employee deleteEmployee(Long id) throws Exception;
	
	
	
	
	
	//public List<Employee> findAllEmployeeByProjectId(Long empId, Long projectId) throws Exception;
}

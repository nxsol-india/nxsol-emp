package com.nxsol.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nxsol.dto.EmployeeDto;
import com.nxsol.dto.ProjectDto;
import com.nxsol.entity.Employee;
import com.nxsol.entity.Project;
import com.nxsol.respository.EmployeeRepository;
import com.nxsol.respository.ProjectRepository;
import com.nxsol.service.EmployeeService; 

@Service
public class EmployeeServiceImpl implements EmployeeService{
	
	@Autowired
	private EmployeeRepository employeeRepository;
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public List<Employee> findAllEmployee() throws Exception {
		return employeeRepository.findAllEmployee();
	}

	@Override
	public Employee findEmployeeById(Long id) throws Exception {
		Employee empExisting = employeeRepository.findEmployeeById(id);
		if(null != empExisting) {
			return employeeRepository.findEmployeeById(id);
		}
		else {
			throw new Exception("Employee Not Found "+id);
		}
	}
	
	@Override
	public Employee addEmployee(EmployeeDto employeeDto) throws Exception {
		Employee employee= new Employee();
		employee.setEmpName(employeeDto.getEmpName());
		employee.setIsDeleted(false);
		
		if(employeeDto.getProjectDto() != null && !employeeDto.getProjectDto().isEmpty()) {
			List<Project> listProject  = new ArrayList<Project>(0);
			for (ProjectDto projectDto : employeeDto.getProjectDto()) {
				Project project = new Project();
				project.setProjectDescription(projectDto.getProjectDescription());
				project.setProjectName(projectDto.getProjectName());
				project.setIsDeleted(false);
				listProject.add(project);
			}
			
			employee.setProject(listProject);
		}
        return employeeRepository.save(employee);
	}

	@Override
	public Employee updateEmployee(EmployeeDto employeeDto) throws Exception {
		Employee empExisting = employeeRepository.findEmployeeById(employeeDto.getId());
		
		if(null != empExisting) {
			empExisting.setEmpName(null != employeeDto.getEmpName() ? employeeDto.getEmpName() : empExisting.getEmpName());
			if(employeeDto.getProjectDto() != null && !employeeDto.getProjectDto().isEmpty()) {
				List<Project> listProject  = new ArrayList<Project>(0);
				for (ProjectDto projectDto : employeeDto.getProjectDto()) {
					Project project = new Project();
					project.setProjectDescription(projectDto.getProjectDescription());
					project.setProjectName(projectDto.getProjectName());
					project.setIsDeleted(false);
					listProject.add(project);
				}
				
				empExisting.setProject(listProject);
			}
			
			return employeeRepository.save(empExisting);
		}
		else {
			throw new Exception("Can't udpate employee");
		}
	}

	@Override
	public Employee deleteEmployee(Long id) throws Exception {
		Employee empExisting = employeeRepository.findEmployeeById(id);
		if(null != empExisting) {
			empExisting.setIsDeleted(true);
			return employeeRepository.save(empExisting);
		}
		else {
			throw new Exception("Can't delete employee having id:"+id);
		}
	}

	

	

	/*@Override
	public List<Employee> findAllEmployeeByProjectId(Long empId, Long projectId) throws Exception {
		List <Employee> emp = employeeRepository.findEmployeeByProjectId(empId, projectId);
		if(!emp.isEmpty()) {
			return emp;
		}
		else {
			throw new Exception("Can't find employee having id:"+empId+" having project having id:"+projectId);
		}
	}*/
}

package com.nxsol.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
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
	public List<EmployeeDto> findAllEmployee() throws Exception {
		List<Employee> employee =  employeeRepository.findAllEmployeesByIsDeletedFalse();

		List<EmployeeDto> employeeDtos = new ArrayList<EmployeeDto>(0);
		for (Employee employee_ : employee) {
			EmployeeDto employeeDto = new EmployeeDto();
			employeeDto.setId(employee_.getId());
			employeeDto.setEmpName(employee_.getEmpName());

			List<ProjectDto> projectDtos = new ArrayList<ProjectDto>(0);
			for (Project project : employee_.getProject()) {
				ProjectDto projectDto = new ProjectDto();
				BeanUtils.copyProperties(project, projectDto);
				projectDtos.add(projectDto);
			}

			employeeDto.setProjectDto(projectDtos);
			employeeDtos.add(employeeDto);
		}
		return employeeDtos;
	}

	@Override
	public EmployeeDto findEmployeeById(Long id) throws Exception {
		Employee empExisting = employeeRepository.findEmployeeById(id);
		if(null != empExisting) {

			EmployeeDto employeeDto = new EmployeeDto();
			employeeDto.setId(empExisting.getId());
			employeeDto.setEmpName(empExisting.getEmpName());

			List<ProjectDto> projectDtos = new ArrayList<ProjectDto>(0);
			for (Project project : empExisting.getProject()) {
				ProjectDto projectDto = new ProjectDto();
				BeanUtils.copyProperties(project, projectDto);
				projectDtos.add(projectDto);
			}

			employeeDto.setProjectDto(projectDtos);
			return employeeDto;
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

				Project project = null;
				if(projectDto.getId() != null) {
					project = projectRepository.findProjectById(projectDto.getId());
				}else {
					project = new Project();
				}
				project.setProjectDescription(null!=projectDto.getProjectDescription() ? projectDto.getProjectDescription() : project.getProjectDescription());
				project.setProjectName(null!= projectDto.getProjectName() ? projectDto.getProjectName() : project.getProjectName());
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
			empExisting.setIsDeleted(null != employeeDto.getIsDeleted() ? employeeDto.getIsDeleted() : empExisting.getIsDeleted());
			if(employeeDto.getProjectDto() != null && !employeeDto.getProjectDto().isEmpty()) {
				List<Project> listProject  = new ArrayList<Project>(0);
				for (ProjectDto projectDto : employeeDto.getProjectDto()) {

					Project project = null;
					if(projectDto.getId() != null) {
						project = projectRepository.findProjectById(projectDto.getId());
					}else {
						project = new Project();
					}
					project.setProjectDescription(null!=projectDto.getProjectDescription() ? projectDto.getProjectDescription() : project.getProjectDescription());
					project.setProjectName(null!= projectDto.getProjectName() ? projectDto.getProjectName() : project.getProjectName());
					project.setIsDeleted(null != projectDto.getIsDeleted() ? projectDto.getIsDeleted() : project.getIsDeleted());
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
}
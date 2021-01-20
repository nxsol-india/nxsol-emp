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
import com.nxsol.respository.ProjectRepository;
import com.nxsol.service.ProjectService;

@Service
public class ProjectServiceimpl implements ProjectService{
	
	@Autowired
	private ProjectRepository projectRepository;
	
	@Override
	public List<ProjectDto> findAllProjects() throws Exception {
		
		List<Project> projects = projectRepository.findAllProjectsByIsDeletedFalse();
		
		List<ProjectDto> projectDtos = new ArrayList<ProjectDto>(0);
		
		for(Project proj : projects) {
			ProjectDto projDto = new ProjectDto();
			projDto.setId(proj.getId());
			projDto.setProjectName(proj.getProjectName());
			projDto.setProjectDescription(proj.getProjectDescription());
			projDto.setIsDeleted(proj.getIsDeleted());
			
			List<EmployeeDto> empDto = new ArrayList<EmployeeDto>(0);
			
			for(Employee emp : proj.getEmployees()) {
				if(emp.getIsDeleted() == false) {
					EmployeeDto employeeDto = new EmployeeDto();
					BeanUtils.copyProperties(emp, employeeDto);
					empDto.add(employeeDto);
				}
			}
			projDto.setEmployeeDto(empDto);
			projectDtos.add(projDto);
		}
		
		return projectDtos;
	}

	@Override
	public ProjectDto findProjectById(Long id) throws Exception {
		
		Project projExisting = projectRepository.findProjectById(id);
		if(null != projExisting) {
			ProjectDto projectDto = new ProjectDto();
			projectDto.setId(projExisting.getId());
			projectDto.setProjectName(projExisting.getProjectName());
			projectDto.setProjectDescription(projExisting.getProjectDescription());
			projectDto.setIsDeleted(projExisting.getIsDeleted());
			
			List<EmployeeDto> employeeDto = new ArrayList<EmployeeDto>(0);
			for (Employee emp: projExisting.getEmployees()) {
				if(emp.getIsDeleted() == false) {
					EmployeeDto empDto = new EmployeeDto();
					BeanUtils.copyProperties(emp, empDto);
					employeeDto.add(empDto);
				}
			}
			projectDto.setEmployeeDto(employeeDto);
			return projectDto;
		}
		else {
			throw new Exception("Project Not Found "+id);
		}
	}

	@Override
	public Project addProject(ProjectDto projectDto) throws Exception {
		Project proj = new Project();
		proj.setProjectName(projectDto.getProjectName());
		proj.setProjectDescription(projectDto.getProjectDescription());
		proj.setIsDeleted(false);
		return projectRepository.save(proj);
	}

	@Override
	public Project updateProject(ProjectDto projectDto) throws Exception {
		Project projectExisting = projectRepository.findProjectByIdAndIsDeletedFalse(projectDto.getId());
		if(null != projectExisting) {
			Project proj = projectExisting;
			proj.setProjectName(null != projectDto.getProjectName() ? projectDto.getProjectName() : projectExisting.getProjectName());
			proj.setProjectDescription(null != projectDto.getProjectDescription() ? projectDto.getProjectDescription() : projectExisting.getProjectDescription());
			projectDto.setIsDeleted(null != projectDto.getIsDeleted() ? projectDto.getIsDeleted() : projectExisting.getIsDeleted());
			return projectRepository.save(proj);
		}
		else {
			throw new Exception("Can't update project");
		}
	}

	@Override
	public Project deleteProject(Long id) throws Exception {
		Project projectExisting = projectRepository.findProjectByIdAndIsDeletedFalse(id);
		if(null != projectExisting) {
			projectExisting.setIsDeleted(true);
			return projectRepository.save(projectExisting);
		}
		else {
			throw new Exception("Can't delete project having id:"+id);
		}
	}
}

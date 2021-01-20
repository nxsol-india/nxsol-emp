package com.nxsol.dto;

import java.util.List;

public class ProjectDto {

	private Long id;
	private String projectName;
	private String projectDescription;
	private Boolean isDeleted;
	
	private List<EmployeeDto> employeeDto;
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectDescription() {
		return projectDescription;
	}
	public void setProjectDescription(String projectDescription) {
		this.projectDescription = projectDescription;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
	public List<EmployeeDto> getEmployeeDto() {
		return employeeDto;
	}
	public void setEmployeeDto(List<EmployeeDto> employeeDto) {
		this.employeeDto = employeeDto;
	}
}

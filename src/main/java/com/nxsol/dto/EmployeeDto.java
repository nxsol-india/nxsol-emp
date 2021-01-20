package com.nxsol.dto;

import java.util.List;

public class EmployeeDto {
	
	private Long id;
	private String empName;
	private Boolean isDeleted;
	private List<ProjectDto> projectDto;
	
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getEmpName() {
		return empName;
	}
	public void setEmpName(String empName) {
		this.empName = empName;
	}
	public List<ProjectDto> getProjectDto() {
		return projectDto;
	}
	public void setProjectDto(List<ProjectDto> projectDto) {
		this.projectDto = projectDto;
	}
	public Boolean getIsDeleted() {
		return isDeleted;
	}
	public void setIsDeleted(Boolean isDeleted) {
		this.isDeleted = isDeleted;
	}
}

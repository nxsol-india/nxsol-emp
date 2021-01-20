package com.nxsol.service;

import java.util.List;

import com.nxsol.dto.ProjectDto;
import com.nxsol.entity.Project;

public interface ProjectService {
	
	public List<ProjectDto> findAllProjects() throws Exception;
	
	public ProjectDto findProjectById(Long id) throws Exception;
	
	public Project addProject(ProjectDto project) throws Exception;
	
	public Project updateProject(ProjectDto projectDto) throws Exception;
	
	public Project deleteProject(Long id) throws Exception;
	
}

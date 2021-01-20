package com.nxsol.service;

import java.util.List;

import com.nxsol.entity.Project;

public interface ProjectService {
	
	public Project addProject(Project project) throws Exception;
	
	public Project updateProject(Project project) throws Exception;
	
	public Project deleteProject(Long id) throws Exception;
	
	public List<Project> findAllProjects() throws Exception;
	
	public Project findProjectById(Long id) throws Exception;
}

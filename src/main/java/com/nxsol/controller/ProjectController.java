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

import com.nxsol.entity.Project;
import com.nxsol.service.ProjectService;

@RestController
@CrossOrigin("*")
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@GetMapping("")
	public List<Project> findAllProjects() throws Exception{
		return projectService.findAllProjects();
	}
	
	@GetMapping("/{id}")
	public Project findProjectById(@PathVariable Long id) throws Exception{
		return projectService.findProjectById(id);
	}
	
	@PostMapping("")
	public Project addNewProject(@RequestBody Project project) throws Exception {
		return projectService.addProject(project);	
	}
	
	@PutMapping("")
	public Project updateProject(@RequestBody Project project) throws Exception {
		return projectService.updateProject(project);	
	}
	
	@DeleteMapping("/{id}")
	public void addNewProject(@PathVariable Long id) throws Exception {
		projectService.deleteProject(id);	
	}
}

package com.nxsol.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;

import com.nxsol.dto.ErrorDetails;
import com.nxsol.dto.ProjectDto;
import com.nxsol.service.ProjectService;

@RestController
@CrossOrigin("*")
@RequestMapping("/project")
public class ProjectController {
	
	@Autowired
	private ProjectService projectService;
	
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<ErrorDetails> handleAllExceptions(Exception ex, WebRequest request) {
	  ErrorDetails errorDetails = new ErrorDetails(new Date(), ex.getMessage(),
	      request.getDescription(false));
	  return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@GetMapping()
	public ResponseEntity<?> findAllProjects() throws Exception{
		return new ResponseEntity<List<ProjectDto>>(projectService.findAllProjects(),HttpStatus.OK);
	}
	
	@GetMapping("/{id}")
	public ProjectDto findProjectById(@PathVariable Long id) throws Exception{
		return projectService.findProjectById(id);
	}
	
	@PostMapping()
	public ResponseEntity<?> addNewProject(@RequestBody ProjectDto projectDto) throws Exception {
		return new ResponseEntity<>(projectService.addProject(projectDto), HttpStatus.OK);	
	}
	 
	@PutMapping()
	public ResponseEntity<?> updateProject(@RequestBody ProjectDto projectDto) throws Exception {
		return new ResponseEntity<>(projectService.updateProject(projectDto), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<?> deleteProject(@PathVariable Long id) throws Exception {
		projectService.deleteProject(id);
		return new ResponseEntity<>(HttpStatus.OK);
		
	}
}

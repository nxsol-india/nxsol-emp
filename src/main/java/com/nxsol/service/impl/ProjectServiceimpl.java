package com.nxsol.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nxsol.entity.Project;
import com.nxsol.respository.ProjectRepository;
import com.nxsol.service.ProjectService;

@Service
public class ProjectServiceimpl implements ProjectService{
	
	@Autowired
	private ProjectRepository projectRepository;

	@Override
	public Project addProject(Project project) throws Exception {
		project.setIsDeleted(false);
		return projectRepository.save(project);
	}

	@Override
	public Project updateProject(Project project) throws Exception {
		Project projectExisting = projectRepository.findProjectById(project.getId());
		if(null != projectExisting) {
			Project proj = projectExisting;
			proj.setProjectName(null !=project.getProjectName() ? project.getProjectName() : projectExisting.getProjectName());
			proj.setProjectDescription(null != project.getProjectDescription() ? project.getProjectDescription() : projectExisting.getProjectDescription());
			project.setIsDeleted(false);
			return projectRepository.save(proj);
		}
		else {
			throw new Exception("Can't update project");
		}
	}

	@Override
	public Project deleteProject(Long id) throws Exception {
		Project projectExisting = projectRepository.findProjectById(id);
		if(null != projectExisting) {
			projectExisting.setIsDeleted(true);
			return projectRepository.save(projectExisting);
		}
		else {
			throw new Exception("Can't delete project having id:"+id);
		}
	}

	@Override
	public List<Project> findAllProjects() throws Exception {
		return projectRepository.findAll();
	}

	@Override
	public Project findProjectById(Long id) throws Exception {
		return projectRepository.findProjectById(id);
	}

}

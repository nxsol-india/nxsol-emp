package com.nxsol.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="project")
public class Project implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "projectName")
	private String projectName;
	
	@Column(name = "description")
	private String projectDescription;
	
	@Column(name = "isDeleted")
	private Boolean isDeleted;

//	public Project(Long id) {
//		super();
//		this.id = id;
//	}
//
//	public Project(Long id, String projectName, String projectDescription, Boolean isDeleted) {
//		super();
//		this.id = id;
//		this.projectName = projectName;
//		this.projectDescription = projectDescription;
//		this.isDeleted = isDeleted;
//	}

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

	@Override
	public String toString() {
		return "Project [id=" + id + ", projectName=" + projectName + ", projectDescription=" + projectDescription
				+ ", isDeleted=" + isDeleted + "]";
	}
}

package com.nxsol.respository;

import java.util.List;

import org.springframework.data.jdbc.repository.query.Query;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nxsol.dto.ProjectDto;
import com.nxsol.entity.Project;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
	
	@Query(value="SELECT proj FROM Project proj WHERE proj.isDeleted = false")
	List<Project> findAllProjectsByIsDeletedFalse();

	@Query(value="SELECT proj FROM Project proj WHERE proj.id =?1")
	Project findProjectById(Long id);
	
	@Query(value="SELECT proj FROM Project proj WHERE proj.id =?1 and proj.isDeleted = false")
	Project findProjectByIdAndIsDeletedFalse(Long id);
}
